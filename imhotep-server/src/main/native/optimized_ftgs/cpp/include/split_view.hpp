#ifndef SPLIT_VIEW_HPP
#define SPLIT_VIEW_HPP

#include <memory>
#include <sstream>

#include "imhotep_error.hpp"
#include "term.hpp"

namespace imhotep {

    class SplitView {
    public:
        typedef std::pair<const char*, const char*> Buffer;

        SplitView(const char* begin = 0,
                  const char* end   = 0)
            : _begin(begin)
            , _end(end) {
            if (_begin > _end) {
                throw std::length_error(__PRETTY_FUNCTION__);
            }
        }

        bool operator==(const SplitView& rhs) const {
            return _begin == rhs._begin && _end == rhs._end;
        }

        bool   empty() const { return size() <= 0;    }
        size_t  size() const { return _end - _begin;  }

        template <typename T>
        T read() {
            const T* result(reinterpret_cast<const T*>(_begin));
            _begin += sizeof(T);
            return T(*result);
        }

        StringRange read_bytes(size_t length) {
            const char* old_begin(_begin);
            _begin += length;
            return StringRange(old_begin, _begin);
        }

    private:
        const char* _begin;
        const char* _end;
    };

    template<>
    inline
    IntTerm SplitView::read<IntTerm>() {
        const int64_t doc_offset(read<int64_t>());
        const int32_t doc_freq(read<int32_t>());
        const int64_t id(read<int64_t>());
        return IntTerm(id, doc_offset, doc_freq);
    }

    template<>
    inline
    StringTerm SplitView::read<StringTerm>() {
        const int64_t     doc_offset(read<int64_t>());
        const int32_t     doc_freq(read<int32_t>());
        const size_t      id_size(read<size_t>());
        const StringRange id(read_bytes(id_size));
        return StringTerm(id, doc_offset, doc_freq);
    }
}

#endif