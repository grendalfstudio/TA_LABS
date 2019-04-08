#include "pch.h"
#include <iostream>
#include <algorithm>
#include <list>

using namespace std;

const int T_FACTOR = 1;

int main()
{
	Btree a;
	/*btree.add(1);
	btree.add(2);
	btree.add(3);
	btree.add(4);
	btree.add(5);
	btree.add(6);
	btree.add(7);
	btree.add(8);*/
}

// Node structure
struct Node
{
	int keys[2 * T_FACTOR - 1];			// Massive of keys
	Node *children[2 * T_FACTOR];		// Massive of pointers to the child subtrees
	Node *parent = nullptr;				// Pointer to the parent node
	int size = 0;						// Number of keys (number of children is 'size' + 1)
	bool isLeaf = false;
};

// B-tree class
class Btree
{
private:
	Node *root;
public:
	/*Btree()
	{
		root->isLeaf = true;	/// root is a leaf at start
	}*/
	void add(int key)
	{
		Node *current = root;						//Iterator to the B-tree nodes
		/// First check the 'root' whether it is full (special case)
		if (current->size == 2 * T_FACTOR - 1)
		{
			Node *leftNode;
			Node *rightNode;
			Node *newRoot;
			int middle = current->keys[T_FACTOR - 1];		// The middle element which will go to the 'newRoot'
			/// Filling the nodes
			for (int i = 0; i < 2 * T_FACTOR - 1; i++)
			{
				if (i != T_FACTOR - 1)					/// We don't push the 'middle' to the nodes
				{
					if (current->keys[i] < middle)		/// Push the keys and its forward child to the 'leftNode'
					{
						leftNode->keys[leftNode->size] = current->keys[i];
						leftNode->children[leftNode->size] = current->children[i];
						leftNode->size++;
					}
					else								/// Push the keys and its forward child to the 'rightNode'
					{
						rightNode->keys[rightNode->size] = current->keys[i];
						rightNode->children[rightNode->size] = current->children[i];
						rightNode->size++;
					}
				}
			}
			/// Setting up the 'leftNode'
			leftNode->children[leftNode->size] = current->children[T_FACTOR - 1];
			leftNode->parent = newRoot;
			/// Setting up the 'rightNode'
			rightNode->children[rightNode->size] = current->children[2 * T_FACTOR - 1];
			rightNode->parent = newRoot;
			/// Adding 'middle' to the 'newRoot'
			newRoot->keys[0] = middle;
			newRoot->children[0] = leftNode;
			newRoot->children[1] = rightNode;
			newRoot->size++;
			root = newRoot;
			/// Step down
			current = (key < middle ? leftNode : rightNode);
		}
		/// Going down the B-tree
		while (current->isLeaf == false)
		{
			if (current->size == 2 * T_FACTOR - 1)
			{
				Node *leftNode;
				Node *rightNode;
				Node *parent = current->parent;
				int middle = current->keys[T_FACTOR - 1];		// The element which will go to the 'parent'
				/// Filling the nodes
				for (int i = 0; i < 2 * T_FACTOR - 1; i++)
				{
					if (i != T_FACTOR - 1)					/// We don't push the 'middle' to the nodes
					{
						if (current->keys[i] < middle)		/// Push the keys and its forward child to the 'leftNode'
	
					{
							leftNode->keys[leftNode->size] = current->keys[i];
							leftNode->children[leftNode->size] = current->children[i];
							leftNode->size++;
						}
						else								/// Push the keys and its forward child to the 'rightNode'
						{
							rightNode->keys[rightNode->size] = current->keys[i];
							rightNode->children[rightNode->size] = current->children[i];
							rightNode->size++;
						}
					}
				}
				/// Setting up the 'leftNode'
				leftNode->children[leftNode->size] = current->children[T_FACTOR - 1];
				leftNode->parent = parent;
				/// Setting up the 'rightNode'
				rightNode->children[rightNode->size] = current->children[2 * T_FACTOR - 1];
				rightNode->parent = parent;
				/// Setting up the 'parent'
				parent->size++;
				parent->children[parent->size] = parent->children[parent->size - 1];	/// It relocates in any case
				int i;		// Iterator to the elements in the 'current'
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
				/// Insertion of the 'middle'
				parent->keys[i] = middle;
				parent->children[i] = leftNode;
				parent->children[i + 1] = rightNode;	/// Replaces the pointer to the 'current'
				/// Step down
				current = (key < middle ? leftNode : rightNode);
			}
		}
		/// Pushing and arranging
		current->keys[current->size] = key;
		current->size++;
		sort(current->keys, current->keys + current->size);
	}

	/*void show(Node& node = this->root)
	{
		if (node.isLeaf == true)
		{
			for (int i = 0; i < node->size; i++)
			{
				cout << node->keys[i] << "_";
			}
			cout << " ";
		}
		else
		{
			for (int i = 0; i < node->size; i++)
			{
				cout << node->keys[i] << endl << cout << show(node.children[i]) << " ";
			}
			cout << endl;
		}
	}*/

	bool less(const int& first, const int& second)
	{
		return (first <= second ? true : false);
	}
};