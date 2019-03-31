// B-tree.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include "pch.h"
#include <iostream>
#include <list>

using namespace std;

const int NODE_SIZE = 50;

int main()
{

}

struct Node
{
	template<typename T> Node()
	{
		T keys[2 * NODE_SIZE];
		Node *children[2 * NODE_SIZE + 1];
		Node *parent;
		int keys_amount = 0,
			children_amount = 0;
		bool isLeaf;

	}
};

class Btree
{
	Node* root;
	template<typename T> Btree()
	{
		root = new Node();
	}

	template <typename T>
	void add(T item)
	{
		Node* current = root;
		// While we are not in a leaf
		while (current.isLeaf == false)
		{	// Move to the necessary keys and children
			bool isLast = true;
			for (int i = 0; i < 2 * NODE_SIZE; i++)
			{
				if (item < current->keys[i])
				{
					current = current->children[i];
					isLast = false;
					break;
				}
			}
			if (isLast == true)
			{
				current = current->children[2 * NODE_SIZE];
			}
		}
		while(Compare(item, current.keys[i]))
	}

	/*template <typename T>
	bool contains(T item) { return true; }

	template <typename T>
	void remove(T item);

	void balance();*/
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
