#include "pch.h"
#include <iostream>
#include <algorithm>
#include <list>

using namespace std;

const int NODESIZE = 50;				// Minimal number of keys in the node

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
	T keys[2 * NODESIZE];				// Massive of keys
	Node *children[2 * NODESIZE + 1];	// Massive of pointers to the child subtrees
	Node *parent = nullptr;				// Pointer to the parent node
	int size = 0;						// Number of keys (number of children is 'size' + 1)
	bool isLeaf = false;				// Flag that shows is it a leaf node
};

// B-tree class
template<typename T> class Btree
{
	Node<T> *root;				// Root of the B-tree
	Btree()
	{
		root->isLeaf = true;	/// Root is a leaf at start
	}
	// Adds a key to the B-tree (tree balances automatically)
	void add(T& key)								//
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
		if (current->size < 2 * NODESIZE)
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

	void balance(Node<T>& divideNode, T& key) // TODO
	{
		///Getting the 'middle' and 'divideNode' ready to make a balancing
		T middle = current->keys[NODESIZE];		///Default: 'middle' = middle key
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
			Node<T> *newLeftNode,
					*newRightNode;

			/* //Getting newRoot ready
			newRoot->keys[0] = middle;
			newRoot->children[0] = newLeftNode;
			newRoot->children[1] = newRightNode;
			newRoot->size = 1;*/

			///Getting newLeftNode ready
			newLeftNode->parent = divideNode.parent;
			newLeftNode->isLeaf = divideNode.isLeaf;
			newLeftNode->size = NODESIZE;
			for (int i = 0; i < NODESIZE; i++)
			{
				newLeftNode->keys[i] = divideNode.keys[i];
				newLeftNode->children[i] = divideNode.children[i];
			}
			newRightNode->children[NODESIZE] = divideNode.children[NODESIZE];			///???????

			///Getting newRightNode ready
			newRightNode->parent = divideNode.parent;
			newRightNode->isLeaf = divideNode.isLeaf;
			newRightNode->size = NODESIZE;
			for (int i = 0; i < NODESIZE; i++)
			{
				newRightNode->keys[i] = divideNode.keys[NODESIZE + i];
				newRightNode->children[i] = divideNode.children[NODESIZE + i];
			}
			newRightNode->children[NODESIZE] = divideNode.children[2 * NODESIZE];		///???????

			///delete the rubbish
			delete divideNode;

		}
		else
		{
			Node<T> *newLeftNode,
					*newRightNode,
					*newRoot;

			///Getting newRoot ready
			newRoot->keys[0] = middle;
			newRoot->children[0] = newLeftNode;
			newRoot->children[1] = newRightNode;
			newRoot->size = 1;

			///Getting newLeftNode ready
			for (int i = 0; i < NODESIZE; i++)
			{
				newLeftNode->keys[i] = divideNode.keys[i];
				newLeftNode->children[i] = divideNode.children[i];
			}
			newRightNode->children[NODESIZE] = divideNode.children[NODESIZE];			///???????

			///Getting newRightNode ready
			for (int i = 0; i < NODESIZE; i++)
			{
				newRightNode->keys[i] = divideNode.keys[NODESIZE + i];
				newRightNode->children[i] = divideNode.children[NODESIZE + i];
			}
			newRightNode->children[NODESIZE] = divideNode.children[2 * NODESIZE];		///???????

			///delete the rubbish
			delete divideNode;
		}
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
