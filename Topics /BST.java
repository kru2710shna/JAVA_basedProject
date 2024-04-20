class BST 
{
    public class Node
    {
        private int value;
        private int height;
        private Node left;
        private Node right;

        // Constructor 
        public Node(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }

    private Node root;

    public BST()
    {
        root = null;
    }

    public int height(Node node)
    {
        if (node == null)
        {
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void insert(int value)
    {
        root = insert(value, root);
    }

    private Node insert(int value, Node node)
    {
        if (node == null)
        {
            node = new Node(value);
            return node;
        }
        if (value < node.value)
        {
            node.left = insert(value, node.left);
        }
        else if (value > node.value)
        {
            node.right = insert(value, node.right);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    public boolean balanced()
    {
        return bal(root);
    }

    private boolean bal(Node node)
    {
        if (node == null)
        {
            return true;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && bal(node.left) && bal(node.right);
    }

    public void display()
    {
        display(root, "Root Node: ");
    }

    private void display(Node node, String details)
    {
        if (node == null)
        {
            return;
        }

        System.out.println(details + " " + node.value + " : ");
        display(node.left, "Left Node " + node.value + " : ");
        display(node.right, "Right Node " + node.value + " : ");
    }
}
