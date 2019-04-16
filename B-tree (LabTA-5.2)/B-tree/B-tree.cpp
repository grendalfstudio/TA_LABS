#include "pch.h"
#include <iostream>
#include <algorithm>
#include <list>

using namespace std;

const int T_FACTOR = 2;

// Node structure
struct Node
{
	int keys[2 * T_FACTOR - 1];			// Massive of keys
	Node* children[2 * T_FACTOR];		// Massive of pointers to the child subtrees
	Node* parent = nullptr;						// Pointer to the parent node
	int size = 0;						// Number of keys (number of children is 'size' + 1)
	bool isLeaf = false;				// Shows is it a leaf node
};

// B-tree class
class Btree
{
private:
	Node* root;
	/// Splits the node into two
	Node* splitNode(Node* current, int key)
	{
		Node* leftNode = new Node();
		Node* rightNode = new Node();
		Node* parent = current->parent;
		int middle = current->keys[T_FACTOR - 1];
		/// Filling 'leftNode'
		leftNode->isLeaf = current->isLeaf;
		leftNode->size = T_FACTOR - 1;
		leftNode->parent = parent;
		for (int i = 0; i < T_FACTOR - 1; i++)
		{
			leftNode->keys[i] = current->keys[i];
			leftNode->children[i] = current->children[i];
			if (leftNode->isLeaf == false)
			{
				leftNode->children[i]->parent = leftNode;
			}
		}
		leftNode->children[T_FACTOR - 1] = current->children[T_FACTOR - 1];
		if (leftNode->isLeaf == false)
		{
			leftNode->children[T_FACTOR - 1]->parent = leftNode;
		}
		/// Filling 'rightNode'
		rightNode->isLeaf = current->isLeaf;
		rightNode->size = T_FACTOR - 1;
		rightNode->parent = parent;
		for (int i = T_FACTOR; i < 2 * T_FACTOR - 1; i++)
		{
			rightNode->keys[i - T_FACTOR] = current->keys[i];
			rightNode->children[i - T_FACTOR] = current->children[i];
			if (rightNode->isLeaf == false)
			{
				rightNode->children[i - T_FACTOR]->parent = rightNode;
			}
		}
		rightNode->children[T_FACTOR - 1] = current->children[2 * T_FACTOR - 1];
		if (rightNode->isLeaf == false)
		{
			rightNode->children[T_FACTOR - 1]->parent = rightNode;
		}
		/// Filling the parent
		parent->size = parent->size + 1;
		parent->children[parent->size] = parent->children[parent->size - 1];	/// It relocates in any case
		int i;		// Iterator to the elements in the 'parent'
		for (i = parent->size - 1; i >= 1; i--)
		{
			if (middle >= parent->keys[i - 1])		/// If we found a place where 'middle' should be placed
			{
				break;								/// Go to the insertion of the 'middle'
			}
			else
			{
				parent->keys[i] = parent->keys[i - 1];			/// Relocating keys
				parent->children[i] = parent->children[i - 1];	/// Relocating children
			}
		}
		parent->keys[i] = middle;
		parent->children[i] = leftNode;
		parent->children[i + 1] = rightNode;	/// Replaces the pointer to the 'current'
		/// Step down
		delete current;
		return (key < middle ? leftNode : rightNode);
	}
public:
	Btree()
	{
		root = new Node();
		root->isLeaf = true;	/// root is a leaf at start
	}

	void add(int key)
	{
		Node* current = root;		//Iterator to the B-tree nodes
		/// First check the 'root' whether it is full (special case)
		if (current->size == 2 * T_FACTOR - 1)
		{
			Node* newRoot = new Node();
			newRoot->children[0] = root;
			root->parent = newRoot;
			current = splitNode(current, key);
			root = newRoot;
		}
		/// Going down the B-tree
		while (current->isLeaf == false)
		{
			if (current->size == 2 * T_FACTOR - 1)
			{
				current = splitNode(current, key);
			}
			else
			{
				int i;		// Iterator to the elements in the 'current'
				for (i = 0; i < current->size; i++)			/// Searching for the necessary child
				{
					if (key < current->keys[i])
					{
						break;
					}
				}
				current = current->children[i];
			}
		}
		/// Pushing and arranging
		if (current->size == 2 * T_FACTOR - 1)
		{
			current = splitNode(current, key);
		}
		current->keys[current->size] = key;
		current->size++;
		sort(current->keys, current->keys + current->size);
	}
	void remove(int key)
	{
		Node* current = root;		//Iterator to the B-tree nodes
		bool keyFound = false;
		/// Going down the B-tree
		while (keyFound == false)
		{
			int i;		// Iterator to the elements in the 'current'
			for (i = 0; i < current->size; i++)			/// Searching for the necessary child
			{
				if (key < current->keys[i])
				{
					break;
				}
			}
			current = current->children[i];
		}


		while (current->isLeaf == false)
		{
			if (current->size == 2 * T_FACTOR - 1)
			{
				current = splitNode(current, key);
			}
			else
			{
				int i;		// Iterator to the elements in the 'current'
				for (i = 0; i < current->size; i++)			/// Searching for the necessary child
				{
					if (key < current->keys[i])
					{
						break;
					}
				}
				current = current->children[i];
			}
		}
		/// Pushing and arranging
		if (current->size == 2 * T_FACTOR - 1)
		{
			current = splitNode(current, key);
		}
		current->keys[current->size] = key;
		current->size++;
		sort(current->keys, current->keys + current->size);
	}
	bool less(const int& first, const int& second)
	{
		return (first <= second ? true : false);
	}
};

int main()
{
	Btree btree;
	btree.add(1);
	btree.add(2);
	btree.add(3);
	btree.add(4);
	btree.add(5);
	btree.add(6);
	btree.add(7);
	btree.add(8);
	btree.add(9);
	btree.add(10);
	btree.add(11);
	btree.add(12);
	btree.add(13);
	btree.add(14);
}