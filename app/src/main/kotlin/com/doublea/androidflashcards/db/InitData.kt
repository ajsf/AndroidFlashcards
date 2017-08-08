package com.doublea.androidflashcards.db

val dataStructureAndAlgorithms = mapOf(
        "Array" to "An Array consists of a group of same data type. It storage on continuous memory space, use index could find address of the element. Array include one dimensional array and multi-dimensional array,one dimensional array is the simplest data structures, and also most commonly used.",

        "LinkedList" to "A LinkedList, just like a tree and unlike an array, consists of a group of nodes which together represent a sequence. Each node contains data and a pointer. The data in a node can be anything, but the pointer is a reference to the next item in the LinkedList. A LinkedList contains both a head and a tail. The Head is the first item in the LinkedList, and the Tail is the last item. A LinkedList is not a circular data structure, so the tail does not have its pointer pointing at the Head, the pointer is just null. The run time complexity for each of the base methods are as follows:\n" +
                "Algorithm \tAverage \tWorst Case\n" +
                "Space \tO(n) \tO(n)\n" +
                "Search \tO(n) \tO(n)\n" +
                "Insert \tO(1) \tO(1)\n" +
                "Delete \tO(1) \tO(1)",

        "DoublyLinkedList" to "A DoublyLinkedList firstly is a LinkedList, but there have two pointer in each node, previous pointer reference to previous node and next pointer reference to next node. DoublyLinkedList also has a head node, head node's next pointer reference to first node.The last node's next pointer reference to NULL, but if last node's next pointer reference to first node called Circular DoublyLinkedList.DoublyLinkedList is very convenient to find previous and next node from each node.",

        "Stack" to "A Stack is a basic data structure with a \"Last-in-First-out\" methodology. Which means that the last item that was added to the stack, is the first item that comes out of the stack. A Stack is like a stack of books. In order to get to the first book that was added in the stack (the bottom book), all of the books that were added after need to be removed first. Adding to a Stack is called a Push, removing from a stack is called a Pop, and getting the last item inserted into the stack without removing it is called Top. The most common way to implement a stack is by using a LinkedList, but there are also StackArray (implemented with an array) which does not replace null entries, and there is also a Vector implementation that does replace null entries.",

        "Queue" to "",

        "PriorityQueue" to "",

        "Dynamic Programming" to "",

        "String Manipulation" to "",

        "Binary Tree" to "",

        "Binary Search Tree" to "",

        "Sorting Algorithms" to "",

        "Hash Table or Hash Map" to "",

        "Breadth First Search" to "",

        "Depth First Search" to "",

        "Greedy Algorithm" to "")

val coreJava = mapOf(
        "Explain OOP Concept." to "Object-Oriented Programming is a methodology to design a program using classes, objects, inheritance, polymorphism, abstraction, and encapsulation.",
        "Differences between abstract classes and interfaces?" to "An abstract class, is a class that contains both concrete and abstract methods (methods without implementations). An abstract method must be implemented by the abstract class sub-classes. Abstract classes are extended. An interface is like a blueprint/contract of a class. It contains empty methods that represent what all of its subclasses should have in common. The subclasses provide the implementation for each of these methods. Interfaces are implemented.",

        "What is serialization? How do you implement it?" to
                "Serialization is the process of converting an object into a stream of bytes in order to store an object into memory so that it can be recreated at a later time while still keeping the objects original state and data. In Android you may use either the Serializable or Parcelable interface. Even though Serializable is much easier to implement, in Android it is highly recommended to use Parcelable instead, as Parcelable was created exclusively for Android which performs about 10x faster then Serializable because Serializable uses reflection which is a slow process and tends to create a lot of temporary objects which may cause garbage collection to occur more often.",

        "What is Singleton class?" to "A singleton is a class that can only be instantiated once.This singleton pattern restricts the instantiation of a class to one object. This is useful when exactly one object is needed to coordinate actions across the system. The concept is sometimes generalized to systems that operate more efficiently when only one object exists, or that restrict the instantiation to a certain number of objects.",

        "What are anonymous classes?" to "",

        "What is the difference between using == and .equals on a string?" to "",

        "What is the hashCode() and equals() used for?" to "",

        "What are these final, finally and finalize?" to "",

        "What is memory leak and how does Java handle it?" to "",

        "What is garbage collector? How it works?" to "All objects are allocated on the heap area managed by the JVM. As long as an object is being referenced, the JVM considers it alive. Once an object is no longer referenced and therefore is not reachable by the application code, the garbage collector removes it and reclaims the unused memory.",

        "Arrays vs ArrayLists." to "",

        "HashSet vs TreeSet." to "",

        "Typecast in Java." to "",

        "Difference between method overloading and overriding." to "Overloading happens at compile-time while Overriding happens at runtime: The binding of overloaded method call to its definition has happens at compile-time however binding of overridden method call to its definition happens at runtime. Static methods can be overloaded which means a class can have more than one static method of same name. Static methods cannot be overridden, even if you declare a same static method in child class it has nothing to do with the same method of parent class. /nThe most basic difference is that overloading is being done in the same class while for overriding base and child classes are required. Overriding is all about giving a specific implementation to the inherited method of parent class. /nStatic binding is being used for overloaded methods and dynamic binding is being used for overridden/overriding methods. Performance: Overloading gives better performance compared to overriding. The reason is that the binding of overridden methods is being done at runtime. /nPrivate and final methods can be overloaded but they cannot be overridden. It means a class can have more than one private/final methods of same name but a child class cannot override the private/final methods of their base class. Return type of method does not matter in case of method overloading, it can be same or different. However in case of method overriding the overriding method can have more specific return type (refer this). Argument list should be different while doing method overloading. Argument list should be same in method Overriding.",

        "What are the access modifiers you know? What does each one do?" to "",
        "Can an Interface extend another Interface?" to "",
        "What does the static word mean in Java?" to "",
        "Can a static method be overridden in Java?" to "",
        "What is Polymorphism? What about Inheritance?" to "",
        "What is the difference between an Integer and int?" to "",
        "Do objects get passed by reference or value in Java? Elaborate on that." to "",
        "What is a ThreadPoolExecutor?" to "",
        "What the difference between local, instance and class variables?" to "",
        "What is reflection?" to "",
        "What are strong, soft and weak references in Java?" to "",
        "What is dependency injection? Can you name few libraries? Have you used any?" to "",
        "What does the keyword synchronized mean?" to "",
        "What does it means to say that a String is immutable?" to "",
        "What are transient and volatile modifiers?" to "",
        "What is the finalize() method?" to "",
        "How does the try{}finally{} works?" to "",
        "What is the difference between instantiation and initialization of an object?" to "",
        "When is a static block run?" to "",
        "Explain Generics in Java?" to "",
        "Difference between StringBuffer and StringBuilder?" to "",
        "How is a StringBuilder implemented to avoid the immutable string allocation problem?" to "",
        "What is Autoboxing and Unboxing?" to "",
        "What’s the difference between an Enumeration and an Iterator?" to "",
        "What is the difference between fail-fast and fail safe in Java?" to "",
        "What is Java priority queue?" to "",
        "What are the design patterns?" to "")
