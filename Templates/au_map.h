//
// Created by Admin on 11.12.2018.
//

#ifndef TEMPLATES_AU_MAP_H
#define TEMPLATES_AU_MAP_H

#include <cstdlib>
#include <type_traits>

template<class NUM>
struct defaultComp
{
    bool operator()(const NUM &num1, const NUM &num2)
    {
        return (num1 == num2);
    }
};

namespace containers {
    template<typename Key, typename Value, typename EQ = defaultComp<Key> >
    class au_map {
    public:
        typedef Key key_type;
        typedef Value value_type;
        typedef value_type &reference;
        typedef value_type const &const_reference;
        typedef value_type *pointer;
        typedef value_type const *const_pointer;
        typedef size_t size_type;

        class node {
            friend class au_map;
        private:
            key_type _key;
            value_type _value;
            node *_next;

        public:
            node(node *next, const key_type &key, const_reference value)
                    : _next(next), _key(key), _value(value) {}

            reference get_value() {
                return _value;
            }

            const_reference get_value() const {
                return _value;
            }

            const key_type &get_key() const {
                return _key;
            }

            void set_value(const_reference value) {
                _value = value;
            }
        };

    private:
        node * tail = nullptr;
        EQ _eq;

        node * _find(const key_type & key) {
            node * nowNode = tail;
            while (nowNode && (!_eq(nowNode->_key, key))) {
                nowNode = nowNode->_next;
            }
            return nowNode;
        }
        node* _copy(node * nowNode) {
            if (nowNode)
                return new node(_copy(nowNode->_next), nowNode->_key, nowNode->_value);
            else
                return nullptr;
        }

    public:
        au_map() {}
        au_map(const au_map & map) {
            tail = _copy(map.tail);
        }
        template <typename _EQ>
        au_map(_EQ eq) {
            _eq = eq;
        }
        node * insert(const key_type & key, const_reference value) {
            if (_find(key))
                return nullptr;
            tail = new node(tail, key, value);
            return tail;
        }
        node * find(const key_type & key) {
            return _find(key);
        }
        void erase(node * position) {
            if (tail == position) {
                node * newTail = tail->_next;
                delete tail;
                tail = newTail;
                return;
            }
            node * prevNode = tail;
            while (prevNode->_next != position)
                prevNode = prevNode->_next;
            node * nextNode = position->_next;
            delete position;
            prevNode->_next = nextNode;
        }
    };
}

#endif //TEMPLATES_AU_MAP_H
