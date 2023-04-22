//This code was adopted from Data Structures and Algorithms in Java / Edition 2 by Robert Lafore
// tree.java
// demonstrates binary search tree

//HW3 QUESTION: provide the implementation of the methods below + TEST all your methods in the main by using the menu in the main (see main method)
//Make sure your code works (either compiled in command line (terminal) or in Eclipse. 



import java.io.*;
import java.util.*;               // for Stack class if needed
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   }  // end class Node  
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree
   //ArrayList<Integer> arr = new ArrayList<Integer>();
// -------------------------------------------------------------
   public Tree()                  // constructor
      { setRoot(null); }            // no nodes in tree yet
   public Node getRoot() {
	   return root;
   }
   public void setRoot(Node root) {
	this.root = root;
   }
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                           // (assumes non-empty tree)
	  Node current = getRoot();               
      while(current.iData != key)        
         {
         if(key < current.iData)         
            current = current.leftChild;
         else                            
            current = current.rightChild;
         if(current == null)             
            return null;                 
         }
      return current;
                  // found it
      }  // end find()
// -------------------------------------------------------------
   public void insert(int id, double dd) //this method inserts a node of (id and dd) into the tree. (We are consider a BINARY SEARCH TREE by iData)
      {
	  Node newNode = new Node();    
      newNode.iData = id;           
      newNode.dData = dd;
      if(getRoot()==null)                
         setRoot(newNode);
      else                          
         {
         Node current = getRoot();       
         Node parent;
         while(true)                
            {
            parent = current;
            if(id < current.iData)  
               {
               current = current.leftChild;
               if(current == null)  
                  {                 
                  parent.leftChild = newNode;
                  return;
                  }
               }  
            else                    
               {
               current = current.rightChild;
               if(current == null)  
                  {                 
                  parent.rightChild = newNode;
                  return;
                  }
               }  
            }  
         }  
      }  // end insert()
//////////////////////////////////////////////////////

   public void traverse(int traverseType) //this method is full implemented see below 
      {
       switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(getRoot());
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(getRoot());
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(getRoot());
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot) //implement preOrder traversal
      {
	   if(localRoot != null)
       {
	       System.out.print(localRoot.iData + " ");
	       preOrder(localRoot.leftChild);
	       preOrder(localRoot.rightChild);
       }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot) //implement in Order traversal
      {
	   if(localRoot != null)
       {
	       inOrder(localRoot.leftChild);
	       System.out.print(localRoot.iData + " ");
	       inOrder(localRoot.rightChild);
       }
      }
   
// -------------------------------------------------------------
   private void postOrder(Node localRoot) //implement postOrder traversal
      {
	   if(localRoot != null)
       {
	       postOrder(localRoot.leftChild);
	       postOrder(localRoot.rightChild);
	       System.out.print(localRoot.iData + " ");
       }
      }


///////////////////////////////////////////////////////////////
   public ArrayList<Integer> inOrderArrayList(Node localRoot, ArrayList<Integer> arr) {
	   if(localRoot != null)
       {
		   inOrderArrayList(localRoot.leftChild, arr);
	       arr.add(localRoot.iData);
	       inOrderArrayList(localRoot.rightChild, arr);
       }
	   
       return arr;
   }
   public ArrayList<Integer> initializeArrayList(){
	   ArrayList<Integer> arr = new ArrayList<>();
	   return inOrderArrayList(root, arr);
   }
   
   private void isBST(Node localRoot) //this method will take a tree as an input and will PRINT to the screen if the tree is a BST or NOT.
    {
    	for(int i=0; i<initializeArrayList().size(); i++) {
    		if(initializeArrayList().get(i)>initializeArrayList().get(i+1))
    			System.out.println("The tree is NOT a BST.");
    		else
    			System.out.println("The tree is a BST.");	
    	}
    } 

// -------------------------------------------------------------
    private Node getSuccessor(Node delNode)
    {
    Node successorParent = delNode;
    Node successor = delNode;
    Node current = delNode.rightChild;   // go to right child
    while(current != null)               // until no more
       {                                 // left children,
       successorParent = successor;
       successor = current;
       current = current.leftChild;      // go to left child
       }
                                         // if successor not
    if(successor != delNode.rightChild)  // right child,
       {                                 // make connections
       successorParent.leftChild = successor.rightChild;
       successor.rightChild = delNode.rightChild;
       }
    return successor;
    }
    
    public Boolean isOrNotBST(Node localRoot) {
    	//System.out.println("Size is " + initializeArrayList().size());
    	for(int i=0; i<initializeArrayList().size()-1; i++) {
    		//System.out.print(initializeArrayList().get(i) + " ");
    		if(initializeArrayList().get(i)>initializeArrayList().get(i+1))
    			return false;
    	}
    	return true;
    }
    
    public void isTreeBST(Node localRoot) //this method will take a tree as an input and will PRINT to the screen if the tree is a BST or NOT.
    {
    	if (isOrNotBST(localRoot))
    		System.out.println("The tree is a BST.");
    	else
    		System.out.println("The tree is NOT a BST.");
    } 
    
    public boolean delete(int key) // delete node with given key (iData) (if there are multiple nodes match key with iData you have to delete all of them.
      {                           // (assumes non-empty list)
	  Node current = getRoot();
      Node parent = getRoot();
        
	  boolean isLeftChild = true;

      while(current.iData != key)        
         {
         parent = current;
         if(key < current.iData)         
            {
            isLeftChild = true;
            current = current.leftChild;
            }
         else                            
            {
            isLeftChild = false;
            current = current.rightChild;
            }
         if(current == null)             
            return false;        
         } 
      // found node to delete

      // if no children, simply delete it
      if(current.leftChild==null &&
                                   current.rightChild==null)
         {
         if(current == getRoot())      
            setRoot(null);                 
         else if(isLeftChild)
            parent.leftChild = null;   
         else                           
            parent.rightChild = null;
         }

      // if no right child, replace with left subtree
      else if(current.rightChild==null)
         if(current == getRoot())
            setRoot(current.leftChild);
         else if(isLeftChild)
            parent.leftChild = current.leftChild;
         else
            parent.rightChild = current.leftChild;

      // if no left child, replace with right subtree
      else if(current.leftChild==null)
         if(current == getRoot())
            setRoot(current.rightChild);
         else if(isLeftChild)
            parent.leftChild = current.rightChild;
         else
            parent.rightChild = current.rightChild;

      else  // two children, so replace with inorder successor
         {
         // get successor of node to delete (current)
         Node successor = getSuccessor(current);

         // connect parent of current to successor instead
         if(current == getRoot())
            setRoot(successor);
         else if(isLeftChild)
            parent.leftChild = successor;
         else
            parent.rightChild = successor;

         // connect successor to current's left child
         successor.leftChild = current.leftChild;
         }  // end else two children
      // (successor cannot have a left child)
      return true;
      }  // end delete()

// -------------------------------------------------------------
    int findHeight(Node root) {
    	if(root == null)
    		return -1;
    	else
    		return Math.max(findHeight(root.leftChild),findHeight(root.rightChild)) + 1;	
    }
    void printTreeLevels(Node root, int level) {
    	if(root == null)
    		return;
    	else if(level == 1) {
    		System.out.print(root.iData + " ");
    	}else {
    		printTreeLevels(root.leftChild, level - 1);
    		printTreeLevels(root.rightChild, level - 1);
    	}
    }
    
    public void displayTreeLevels() // this method will display the nodes at each level in the tree. (The method should print the nodes (id) as: Level1:.... - Level2:... 
      {
	   for(int i=1; i<=findHeight(getRoot())+1; i++) {
		   System.out.print("Level" + i + ": ");
		   printTreeLevels(getRoot(), i);
		   System.out.println();
	   }
	   System.out.println();
      }  // end displayTreeLevels()




// -------------------------------------------------------------

  public void displaymyChilds(int id, double dd) //given a node who idata is id and dd is ddata display it childen in the following way:
  {
    //Left child: idata:  dData: 
    //Right child: idata: dData: 

    //if the node does not have children you display message that the nodes Do not have children. 
    // or if one of the child is null, then you display a message stating that. 

	  Node newNode = new Node();
	  newNode = find(id);
	  if(newNode.leftChild == null && newNode.rightChild == null) {
		  System.out.println("The node does not have children.");
	  }else if(newNode.leftChild == null) {
		  System.out.println("The node does not have a left child.\n"
		  		+ "Right child: idata: " + newNode.rightChild.iData + " dData: " + newNode.rightChild.dData);
	  }else if(newNode.rightChild == null) {
		  System.out.println("The node does not have a right child.\n"
				+ "Left child: idata: " + newNode.leftChild.iData + " dData: " + newNode.leftChild.dData);
	  }else {
		  System.out.println("Left child: idata: " + newNode.leftChild.iData + " dData: " + newNode.leftChild.dData);
		  System.out.println("Right child: idata: " + newNode.rightChild.iData + " dData: " + newNode.rightChild.dData);
	  }
  }


// -------------------------------------------------------------

	public void displayLeaves() //this method will display all the leaves (iData and dData) of all the leaves)
	  {
		displayLeavesHelper(getRoot());
	  }
	public void displayLeavesHelper(Node root){
		if(root == null) {
			System.out.println("There is no leaf.");
		}
		if(root.leftChild == null && root.rightChild == null) {
			System.out.println(root.iData + ", " + root.dData);
		}
		if(root.leftChild != null)
			displayLeavesHelper(root.leftChild);
		if(root.rightChild != null)
			displayLeavesHelper(root.rightChild);	
	}



// -------------------------------------------------------------



}  // end class Tree


////////////////////////////////////////////////////////////////
class HW3Trees
   {
   public static void main(String[] args) throws IOException
      {

      //You can modify this code of the main as much as you want - as longs as  ALL the methods above are being tested and called. 


      int value;
      int idata;
      Double dData;

      Tree theTree = new Tree();

       //... you change these inputs to build the tree, and/or can add other inputs to test the program. 
      //The tree is ordered by iData.  


      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);
     
      

//      Menu:
      while(true)
      {
      System.out.println("ONLY enter a number: ");
      System.out.println("1. Traverse\n"
      		+ "2. isBST\n"
      		+ "3. Delete\n"
      		+ "4. Display Tree by Levels\n"
      		+ "5. Display my Childs\n"
      		+ "6. Insert a New Node\n"
      		+ "7. Display All the Leaves\n"
      		+ "8. find");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      switch(choice)
         {
         case 1:	 
	//      1. Traverse
	      	theTree.traverse(1);
	      	theTree.traverse(2);
	      	theTree.traverse(3);
	      	break;
	      	
         case 2:
	//      2. isBST
	      	//System.out.println("Size is " + theTree.inOrderArrayList(theTree.getRoot(),theTree.arr).size());
	      	theTree.isTreeBST(theTree.getRoot());
	      	break;
	      	
         case 3:
	//      3. Delete 
	      	System.out.print("Enter iData to delete: ");
	        idata = input.nextInt();
	        boolean didDelete = theTree.delete(idata);
	        if(didDelete)
	           System.out.print("Deleted " + idata + '\n');
	        else 
	        {
	           System.out.print("Could not delete ");
	           System.out.print(idata + '\n');
	        }
	        break;
	        
	        
         case 4:
	//      4. Display Tree by Levels
	        theTree.displayTreeLevels();
	        break;
	        
         case 5:
	//      5. Display my Childs
	        System.out.println("Displaying childs now...");
	        System.out.print("Enter idata: ");
	        idata = input.nextInt();
	        System.out.print("Enter dData: ");
	        dData = input.nextDouble();
	        theTree.displaymyChilds(idata, dData);
	        break;
	        
	     case 6:
	//      6. Insert a New Node
	        System.out.print("Enter idata to insert: ");
	        idata = input.nextInt();
	        System.out.print("Enter dData to insert: ");
	        dData = input.nextDouble();
	        theTree.insert(idata, dData);
	        break;
	        
	     case 7:   
	//      7. Display All the Leaves
	        theTree.displayLeaves();
	        break;
        
	     case 8:
	//      8. Find
	        System.out.print("Enter iData to find: ");
	        idata = input.nextInt();
	        Node found = theTree.find(idata);
	        if(found != null)
	           {
	           System.out.print("Found: " + found.iData + " " + found.dData);
	           System.out.print("\n");
	           }
	        else
	        {
	           System.out.print("Could not find ");
	           System.out.print(idata + '\n');
	        }
	        break;
        }
      }
// -------------------------------------------------------------
      }  // end class TreeApp
  }
////////////////////////////////////////////////////////////////
