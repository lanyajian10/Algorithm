**目录**

dataStructures.test1.ChessArr             稀疏数组 <===> 二维数组

        ArrToChess             二维数组转稀疏数组 以及 棋盘转存Demo

dataStructures.test2.ArrAsQueue           数组队列

        ArrAsQueueDemo1        数组模仿队列FIFO
        CricleArrQueueDemo2    数组模仿可复用队列

dataStructures.test3.LinkedList           链表队列

        Demo1                  链表结构介绍
        SingleLinkedListDemo   单向链表相关操作
        DoubleLinkedListDemo   双向链表
        CircleLinkedListDemo   环形链表，约瑟夫问题

dataStructures.test4.Stack                栈

        ArrStackDemo           底层为数组的栈结构，模拟简单计算器
        LinkedStackDemo        底层为链表的栈结构
        calculatorDemo         中后缀转化以及后缀计算器算法   逆波兰计算器

algorithm.test5.Recursion       算法

        MiGong                 迭代和回溯算法，迷宫问题
        Qyeen8                 8皇后问题
        
algorithm.test6.Sort            排序算法

        Demo                    算法种类、时间复杂度，排序测试入口
        Sort1bubbing            冒泡排序
        Sort2Choose             选择排序
        Sort3Insert             插入排序
        Sort4Shell              希尔排序
        Sort5Quick              快速排序
        Sort6Merget             归并排序
        Sort7Radix              基数排序
        Sort8Heap               堆排序
    
algorithm.test7.Search          查找算法

        Demo                     查找算法:二分查找/折半查找,插值查找,斐波那契查找
        Search1Binary            二分查找/折半查找
        Search1Binary            插值查找
        Search1Fibonacci         斐波那契查找
        
dataStructures.test8.HashTable            哈希表数据结构

        Demo                     哈希表数据结构
        ArrAndLinkedToHT         数组+链表模拟哈希表结构
        
dataStructures.test9.Tree                 树结构

        Demo                    树
        Tree1Binary             二叉树 前序中序后序遍历、查找
        Tree2ArrBinaryTree      顺序存储二叉树的 前中后序遍历
        Tree3ThreadBinary       线索二叉树  前中后序的创建和遍历
        Tree4Huffman            赫/霍夫曼树  创建
        
algorithm.test10.HuffmanCode    霍夫曼编码

        HuffmanCode             赫夫曼编码、压缩解压
        
dataStructures.test11.BinarySortTree      二分查找树

        BinarySortTreeDemo      二叉排序树节点的添加、删除、中序遍历
        
dataStructures.test12.AVLTree            AVL树，BST的改进，   B树（B-树），B+树，2-3树，3-4树，B*树

        AVLTreeDemo             AVL树，左旋转，右旋转，双旋转
        
algorithm.test13.Graph    图

        GraphDemo               图的创建、深度优先算法、广度优先算法
        
algorithm.test14.divideAndConquer 分治算法
        
        hanoiTower              分治算法之-----汉诺塔算法
        
algorithm.test15.dynamic  动态规划算法

        knapsackProblem         动态规划算法之----背包问题
        
algorithm.test16.KMP      匹配算法

        violentMatch            暴力匹配算法（k1去k2中匹配，匹配失败，k1从头）
        KMP                     KMP匹配算法
        
algorithm.test17.greed      贪心算法

        greedAlgorithm      贪心算法（建造电台覆盖所有村庄）
        
algorithm.test18.prm        普利姆算法(最小生成树)  

        primAlgorithm       普利姆算法（连接各村庄最短线路）
                            村庄越少，连线越稠，时间复杂度越低
                            邻接矩阵：O(v^2) 邻接表O(elog2v)
        
algorithm.test19.kruskal    克鲁斯卡尔算法（最小生成树）

        kruskalAlgorithm    克鲁斯算法（公交站问题）
                            连线越稀，时间复杂度越低
                            O(eloge)

algorithm.test20.dijkstra   迪杰斯特拉算法

        dijkstraAlgorithm   迪杰斯特达算法

algorithm.test21.floyd      弗洛伊德算法

        floydAlgorithm      弗洛伊德算法（从每个顶点出发，到所有顶点的最短路径）
                            邻接矩阵：O(v^3)
                            
algorithm.test22.horse      马踏棋盘算法

        horseChessBoard     马踏棋盘算法及其贪心算法优化