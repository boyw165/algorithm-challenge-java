// Copyright (c) 2016-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.my.myalgorithm.challenge.tree;

/**
 * A trie, also called digital tree and sometimes radix tree or prefix tree
 * (as they can be searched by prefixes), is a kind of search tree.
 * <br/>
 * <br/>
 * Complexity
 * <br/>
 * Time: average O(n), n is the length of the string.
 * <br/>
 * Space: worst O(nm) auxiliary, n is the average length of the string and
 * m is the number of the number of the strings.
 * <br/>
 * <br/>
 * Wiki: https://en.wikipedia.org/wiki/Trie
 * <br/>
 * <pre>
 *
 * 1) An empty AtoZTrie
 *       {}  <= root
 *
 * 1) add "key"
 *       {k} <= root
 *       /
 *     {e}
 *     /
 *   {y}
 *   /
 * {}
 *
 * 2) add "word"
 *       {k,w} <= root
 *       /  \
 *     {e}  {o}
 *     /     \
 *   {y}     {r}
 *   /        \
 * {}         {d}
 *             \
 *             {}
 *
 * 3) add "keyword"
 *                {k,w} <= root
 *                /  \
 *              {e}  {o}
 *              /     \
 *            {y}     {r}
 *            /        \
 *          {w}        {d}
 *          /           \
 *        {o}           {}
 *        /
 *      {r}
 *      /
 *    {d}
 *
 * 4) add "keyboard"
 *                {k,w} <= root
 *                /  \
 *              {e}  {o}
 *              /     \
 *            {y}     {r}
 *            /\       \
 *          {w}{b}     {d}
 *          /   \       \
 *        {o}   {o}     {}
 *        /      \
 *      {r}      {a}
 *      /         \
 *    {d}         {r}
 *                 \
 *                 {d}
 *
 * </pre>
 */
public class AtoZTrie {

    private Node mRoot = new Node(null);

    public void insert(String word) {
        if (word == null || word.length() == 0) return;

        // To lower case.
        word = word.toLowerCase();
        Node node = mRoot;
        int i = 0;
        while (i < word.length()) {
            char c = word.charAt(i++);
            Node childNode = findChildBy(node, c);
            if (childNode == null) {
                // Add the key to the node.
                childNode = addChild(node, c);
            }
            node = childNode;
        }

        node.endOfWord = true;
    }

    public void delete(String word) {
        // TODO: Complete it.
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;

        // To lower case.
        word = word.toLowerCase();
        Node node = mRoot;
        int i = 0;
        while (node != null && i < word.length()) {
            char c = word.charAt(i++);
            node = findChildBy(node, c);
        }

        return i == word.length() &&
               node != null &&
               node.endOfWord;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;

        Node node = mRoot;
        int i = 0;
        while (node != null && i < prefix.length()) {
            char c = prefix.charAt(i++);
            node = findChildBy(node, c);
        }

        return node != null &&
               i == prefix.length();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private Node findChildBy(Node node, char c) {
        if (node == null ||
            c < 'a' ||
            c > 'z') return null;
        int i = c - 'a';

        return node.children[i];
    }

    private Node addChild(Node parent, char c) {
        if (parent == null ||
            c < 'a' ||
            c > 'z') return null;
        int i = c - 'a';

        Node child = new Node(parent);

        parent.children[i] = child;

        return child;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Node {

        Node parent = null;
        Node[] children = new Node[26];
        boolean endOfWord = false;

        Node(Node parent) {
            this.parent = parent;
        }
    }
}