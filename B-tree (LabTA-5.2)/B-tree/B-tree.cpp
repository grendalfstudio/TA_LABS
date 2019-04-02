#include "pch.h"
#include <iostream>
#include <algorithm>
#include <list>

using namespace std;

const int T_FACTOR = 50;

int main()
{
	int a[] = { 0, 2, 4, 6, 8, 10 };
	int b = 3;
	auto *w = lower_bound(a, a + 6, b);
	cout << *w;
}

// Node structure
template<typename T> struct Node
{
	T keys[2 * T_FACTOR - 1];			// Massive of keys
	Node *children[2 * T_FACTOR];		// Massive of pointers to the child subtrees
	Node *parent = nullptr;				// Pointer to the parent node
	int size = 0;						// Number of keys (number of children is 'size' + 1)
	bool isLeaf = false;
};

// B-tree class
template<typename T> class Btree
{
	Node<T> *root;
	Btree()
	{
		root->isLeaf = true;	/// newRoot is a leaf at start
	}
	// Adds a key to the B-tree (tree balances automatically)
	/*void add(T& key)								//
	{
		Node<T> *current = root;					//Iterator to the B-tree nodes
		///Searching the nessesary node
		while (current->isLeaf == false)			///While we are not in a leaf
		{											///Move to the necessary child
			bool isLastChild = true;				// Flag that shows is it a last child that is necessary to choose
			for (int i = 0; i < current->size; i++)
			{
				if (key < current->keys[i])		//It can be changed to comparable function 'less()'
				{	/// If true it means that we found a key
					/// BEFORE which we should add 'key'
					current = current->children[i];	///Go down to the child subtree
					isLastChild = false;
					break;
				}
			}
			if (isLastChild == true)				///Special case
			{
				current = current->children[current->size];
			}
		}
		/// Adding 'key'
		if (current->size < 2 * T_FACTOR)
		{
			current->keys[current->size] = key;
			current->size++;
			sort(current->keys, current->keys + current->size, less);
		}
		else { balance(current, key); }
	}

	// TODO
	/*
	bool contains(T key) { return true; }

	void remove(T key);*/

	/*void balance(Node<T>& divideNode, T& key) // TODO
	{
		///Setting the 'middle' and 'divideNode' ready to make a balancing
		T middle = current->keys[T_FACTOR];		///Default: 'middle' = middle key
			///Checks if the 'key' is a middle key
		T *keyPosition = lower_bound(current->keys, current->keys + current->size, key, less);
		if (*keyPosition == middle)
		{
			middle = key;										///'middle' must always contain a superfluous(зайвий) element
		}
		else
		{
			current->keys[keyPosition - current->keys] = key;	///and 'current->keys' must contain all other keys
		}

		if (divideNode.parent != nullptr)
		{
			Node<T> *leftNode,
					*rightNode;

			/* //Setting newRoot ready
			newRoot->keys[0] = middle;
			newRoot->children[0] = leftNode;
			newRoot->children[1] = rightNode;
			newRoot->size = 1;

			///Setting leftNode ready
			leftNode->parent = divideNode.parent;
			leftNode->isLeaf = divideNode.isLeaf;
			leftNode->size = T_FACTOR;
			for (int i = 0; i < T_FACTOR; i++)
			{
				leftNode->keys[i] = divideNode.keys[i];
				leftNode->children[i] = divideNode.children[i];
			}
			rightNode->children[T_FACTOR] = divideNode.children[T_FACTOR];			///???????

			///Setting rightNode ready
			rightNode->parent = divideNode.parent;
			rightNode->isLeaf = divideNode.isLeaf;
			rightNode->size = T_FACTOR;
			for (int i = 0; i < T_FACTOR; i++)
			{
				rightNode->keys[i] = divideNode.keys[T_FACTOR + i];
				rightNode->children[i] = divideNode.children[T_FACTOR + i];
			}
			rightNode->children[T_FACTOR] = divideNode.children[2 * T_FACTOR];		///???????

			///delete the rubbish
			delete divideNode;

		}
		else
		{
			Node<T> *leftNode,
					*rightNode,
					*newRoot;

			///Setting newRoot ready
			newRoot->keys[0] = middle;
			newRoot->children[0] = leftNode;
			newRoot->children[1] = rightNode;
			newRoot->size = 1;

			///Setting leftNode ready
			for (int i = 0; i < T_FACTOR; i++)
			{
				leftNode->keys[i] = divideNode.keys[i];
				leftNode->children[i] = divideNode.children[i];
			}
			rightNode->children[T_FACTOR] = divideNode.children[T_FACTOR];			///???????

			///Setting rightNode ready
			for (int i = 0; i < T_FACTOR; i++)
			{
				rightNode->keys[i] = divideNode.keys[T_FACTOR + i];
				rightNode->children[i] = divideNode.children[T_FACTOR + i];
			}
			rightNode->children[T_FACTOR] = divideNode.children[2 * T_FACTOR];		///???????

			///delete the rubbish
			delete divideNode;
		}
	}*/
	
	void add(T& key)
	{
		Node<T> *current = root;						//Iterator to the B-tree nodes
		/// First check the root whether it is full (special case)
		if (current->size == 2 * T_FACTOR - 1)
		{
			Node<T> *leftNode;
			Node<T> *rightNode;
			Node<T> *newRoot;
			T middle = current->keys[T_FACTOR - 1];		// The element which will go to the 'newRoot'
			/// Pushing the nodes
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
			/// Setting up the 'newRoot'
			newRoot->keys[0] = middle;
			newRoot->children[0] = leftNode;
			newRoot->children[1] = rightNode;
			newRoot->size++;
			/// Setting up the 'leftNode'
			leftNode->children[leftNode->size] = current->children[T_FACTOR - 1];
			leftNode->parent = newRoot;
			/// Setting up the 'rightNode'
			rightNode->children[rightNode->size] = current->children[2 * T_FACTOR - 1];
			rightNode->parent = newRoot;
			/// Step down
			current = (key < middle ? leftNode : rightNode);
		}
		/// Going down the B-tree
		while (current->isLeaf == false)
		{
			if (current->size == 2 * T_FACTOR - 1)
			{
				Node<T> *leftNode;
				Node<T> *rightNode;
				Node<T> *parent = current->parent;
				T middle = current->keys[T_FACTOR - 1];		// The element which will go to the 'current->parent'
				/// Pushing the nodes
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
				/// Setting up the 'parent'
				for (int i = parent->size; i >= 1; i--)
				{
					if(x >= parent->keys[i - 1])
				}
				parent->keys[parent->size] = key;
				parent->size++;
				/// Setting up the 'leftNode'
				leftNode->children[leftNode->size] = current->children[T_FACTOR - 1];
				leftNode->parent = parent;
				/// Setting up the 'rightNode'
				rightNode->children[rightNode->size] = current->children[2 * T_FACTOR - 1];
				rightNode->parent = parent;
				/// Step down
				current = (key < middle ? leftNode : rightNode);
			}

			/*if (current->size == 2 * T_FACTOR - 1)
			{	/// Split up the current node
				Node<T> *newLeftNode,
					*newRightNode,
					*newRoot;
				 //Setting newRoot ready
				newRoot->keys[0] = middle;
				newRoot->children[0] = newLeftNode;
				newRoot->children[1] = newRightNode;
				newRoot->size = 1;

				///Setting leftNode ready
				newLeftNode->parent = divideNode.parent;
				newLeftNode->isLeaf = divideNode.isLeaf;
				newLeftNode->size = T_FACTOR;
				for (int i = 0; i < T_FACTOR; i++)
				{
					newLeftNode->keys[i] = divideNode.keys[i];
					newLeftNode->children[i] = divideNode.children[i];
				}
				newRightNode->children[T_FACTOR] = divideNode.children[T_FACTOR];			///???????

				///Setting rightNode ready
				newRightNode->parent = divideNode.parent;
				newRightNode->isLeaf = divideNode.isLeaf;
				newRightNode->size = T_FACTOR;
				for (int i = 0; i < T_FACTOR; i++)
				{
					newRightNode->keys[i] = divideNode.keys[T_FACTOR + i];
					newRightNode->children[i] = divideNode.children[T_FACTOR + i];
				}
				newRightNode->children[T_FACTOR] = divideNode.children[2 * T_FACTOR];		///???????

				///delete the rubbish
				delete divideNode;
			}

			bool isLastChild = true;				// Flag that shows is it a last child that is necessary to choose

			for (int i = 0; i < current->size; i++)
			{
				if (key < current->keys[i])		//It can be changed to comparable function 'less()'
				{	/// If true it means that we found a key
					/// BEFORE which we should add 'key'
					current = current->children[i];	///Go down to the child subtree
					isLastChild = false;
					break;
				}
			}
			if (isLastChild == true)				///Special case
			{
				current = current->children[current->size];
			}*/
		}
		/// Pushing and arranging
		current->keys[current->size] = key;
		current->size++;
		sort(current->keys, current->keys + current->size);
	}

	bool less(T& first, T& second)
	{
		return (first <= second ? true : false);
	}
};

// Запуск программы: CTRL+F5 или меню "Отладка" > "Запуск без отладки"
// Отладка программы: F5 или меню "Отладка" > "Запустить отладку"

// Советы по началу работы 
//   1. В окне обозревателя решений можно добавлять файлы и управлять ими.
//   2. В окне Team Explorer можно подключиться к системе управления версиями.
//   3. В окне "Выходные данные" можно просматривать выходные данные сборки и другие сообщения.
//   4. В окне "Список ошибок" можно просматривать ошибки.
//   5. Последовательно выберите пункты меню "Проект" > "Добавить новый элемент", чтобы создать файлы кода, или "Проект" > "Добавить существующий элемент", чтобы добавить в проект существующие файлы кода.
//   6. Чтобы снова открыть этот проект позже, выберите пункты меню "Файл" > "Открыть" > "Проект" и выберите SLN-файл.