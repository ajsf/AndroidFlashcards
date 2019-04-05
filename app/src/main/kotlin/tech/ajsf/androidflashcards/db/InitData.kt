package tech.ajsf.androidflashcards.db

import tech.ajsf.androidflashcards.model.Category
import tech.ajsf.androidflashcards.model.Flashcard

val dataStructureAndAlgorithms = mapOf(
    "Array" to "An Array consists of a group of elements of the same data type. It is stored contiguously in memory and by using its' index, you can find the underlying data. Arrays can be one dimensional and multi-dimensional. One dimensional array is the simplest data structure, and also most commonly used. It is worth noting that in Java language multi-dimensional arrays are implemented as arrays of arrays. For example, int[10][5] is actually one array with its' cells pointing to ten 5-element arrays." +
            "Algorithm \tAverage \tWorst Case\n" +
            "Space \tΘ(n) \tO(n)\n" +
            "Search \tΘ(n) \tO(n)\n" +
            "Insert \tΘ(n) \tO(n)\n" +
            "Delete \tΘ(n) \tO(n)",

    "LinkedList" to "A LinkedList, just like a tree and unlike an array, consists of a group of nodes which together represent a sequence. Each node contains data and a pointer. The data in a node can be anything, but the pointer is a reference to the next item in the LinkedList. A LinkedList contains both a head and a tail. The Head is the first item in the LinkedList, and the Tail is the last item. A LinkedList is not a circular data structure, so the tail does not have its pointer pointing at the Head, the pointer is just null. The run time complexity for each of the base methods are as follows:\n" +
            "Algorithm \tAverage \tWorst Case\n" +
            "Space \tO(n) \tO(n)\n" +
            "Search \tO(n) \tO(n)\n" +
            "Insert \tO(1) \tO(1)\n" +
            "Delete \tO(1) \tO(1)",

    "DoublyLinkedList" to "A DoublyLinkedList is based on a LinkedList, but there is two pointers in each node, \"previous\" pointer holds reference to the previous node and \"next\" pointer holds reference to the next node. It also has a Head node, head node's next pointer references the first node in this DoublyLinkedList. The last node's \"next\" reference points to null, but if last node's next pointer points to the first node, such DoublyLinkedList is called \"Circular DoublyLinkedList\". This data structure is very convenient if you need to be able to traverse stored elements in both directions." +
            "Algorithm \tAverage \tWorst Case\n" +
            "Space \tΘ(n) \tO(n)\n" +
            "Search \tΘ(n) \tO(n)\n" +
            "Insert \tΘ(1) \tO(1)\n" +
            "Delete \tΘ(1) \tO(1)",

    "Stack" to "A Stack is a basic data structure with a \"Last-in-First-out\" (LIFO) semantics. This means that the last item that was added to the stack is the first item that comes out of the stack. A Stack is like a stack of books in that in order to get to the first book that was added in the stack (the bottom book), all of the books that were added after need to be removed first. Adding to a Stack is called \"Push\", removing from a stack is called \"Pop\", and getting the last item inserted into the stack without removing it is called \"Top\". The most common way to implement a stack is by using a LinkedList, but there is also StackArray (implemented with an array) which does not replace null entries, and there is also a Vector implementation that does replace null entries." +
            "Space \tΘ(n) \tO(n) \t\n" +
            "Search \tΘ(n) \tO(n)\n" +
            "Insert (Push) \tΘ(1) \tO(1)\n" +
            "Delete (Pop) \tΘ(1) \tO(1)\n" +
            "Top \tΘ(1) \tO(1)",

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

    "Greedy Algorithm" to ""
)

val coreJava = mapOf(
    "Explain OOP Concepts." to "Object-Oriented Programming is a methodology to design a program using classes, objects, inheritance, polymorphism, abstraction, and encapsulation.",

    "Differences between abstract classes and interfaces?" to "An abstract class, is a class that contains both concrete and abstract methods (methods without implementations). An abstract method must be implemented by the abstract class sub-classes. Abstract classes are extended.\nAn interface is like a blueprint/contract of a class. It contains empty methods that represent what all of its subclasses should have in common. The subclasses provide the implementation for each of these methods. Interfaces are implemented.",

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
    "What are the design patterns?" to ""
)

val coreAndroid = mapOf(
    "What are the Android application components" to "",
    "What is the structure of an Android Application?" to "",
    "What is Context? How is it used?" to "",
    "What is AndroidManifest.xml" to "",
    "What is an Application class?" to "",
    "What is an Activity" to "",
    "Explain Activity and Fragment lifecycle." to "",
    "What are \"launch modes\"?" to "",
    "What is a Fragment?" to "",
    "What is the difference between a Fragment and an Activity? Explain the relationship between the two." to "",
    "Why is it recommended to use only the default constructor to create a Fragment?" to "",
    "How would you communicate between two Fragments?" to "",
    "What is a retained Fragment?" to "",
    "What is View in Android?" to ""
)

val architecture = mapOf(
    "Describe the architecture of your last app" to "",
    "Describe MVP" to "",
    "Describe MVVM" to "",
    "Describe MVC" to "",
    "Describe MVI" to "",
    "Describe the repository pattern" to "",
    "What is clean code?" to "",
    "What is dependency injection?" to ""
)

val designProblems = mapOf(
    "Design Uber" to "",
    "Design Facebook" to "",
    "Design Facebook Nearby Friends" to "",
    "Design WhatsApp" to "",
    "Design SnapChat" to "",
    "What are design problems of a location based app?" to ""
)

val tools = mapOf(
    "Git" to "",
    "RxJava" to "",
    "Dagger" to "",
    "Android Architecture Components" to "",
    "Firebase" to ""
)

val test = mapOf(
    "What is Espresso?" to "",
    "What is Robolectric?" to "",
    "What are the disadvantages of using Roboelectric?" to "",
    "What is UI-Automator? Developer Android" to "",
    "Explain a unit test." to "",
    "Explain an instrumented test." to "",
    "Have you done unit testing or automatic testing?" to "",
    "Why Mockito is used?" to "",
    "Describe a JUnit test." to ""
)

val other = mapOf(
    "What is Android Jetpack?" to "",
    "Describe how REST APIs work. What is REST?" to "",
    "Describe other forms of web API architecture." to "",
    "Describe SQLite. " to "",
    "Describe database." to "",
    "Project Management tools - trello, basecamp, kanban, jira, asana." to "",
    "About build System - gradle, maven, ant, buck." to "",
    "About multiple apk for android application. " to "",
    "Reverse Engineering an APK." to "",
    "What is proguard used for? " to "",
    "What is obfuscation? What is it used for? What about minification?" to "",
    "How do you build your apps for release?" to "",
    "How do you control the application version update to specific number of users?" to "",
    "Can we identify users who have uninstalled our application?" to "",
    "Implement Search Using RxJava Operators. " to "",
    "APK Size Reduction. " to "",
    "Android Development Best Practices. " to "",
    "Android Code Style And Guidelines. " to "",
    "Have you tried Kotlin? " to "",
    "What are the metrics that you should measure continuously while android application development? " to "",
    "What is Chrome Custom Tabs? How to display web content in your app? " to ""
)

fun allFlashcards() = listOf(
    coreJava.toFlashcards(Category.JAVA),
    coreAndroid.toFlashcards(Category.ANDROID),
    other.toFlashcards(Category.OTHER),
    designProblems.toFlashcards(Category.DESIGN),
    test.toFlashcards(Category.TESTING),
    tools.toFlashcards(Category.TOOLS),
    dataStructureAndAlgorithms.toFlashcards(Category.DATA),
    architecture.toFlashcards(Category.ARCHITECTURE)
).flatten()

private fun Map<String, String>.toFlashcards(category: Category): List<Flashcard> =
    map { Flashcard(it.key, it.value, category.toString()) }
