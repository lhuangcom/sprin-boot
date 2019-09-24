import java.util.List;

/**
 * @author LHuang
 * @since 2019/6/21
 */
public class Tree {

    private static Node root;

    public static Node find(int data){

        Node p = root;
        while (p != null){
            if (p.data == data){
                return p;
            }
            if (data > p.data){
                p = p.right;
            }
            else{
                 p = p.left;
            }
        }

        return null;

    }

    public static void insert(int data){

        Node tree = root;
        if (tree == null){
            root = new Node(data);
            return ;
        }
        while (tree != null){
            if (data > tree.data){
                if (tree.right == null){
                    tree.right = new Node(data) ;
                    return;
                }
                tree = tree.right;
            }
            else{
                if (tree.left == null){
                    tree.left = new Node(data) ;
                    return;
                }
                tree = tree.left;
            }
        }

    }

    public static void delete(int data){
        // node 指向要删除的节点，初始化指向根节点
        Node node = root;
        // node的父节点
        Node node_p = null;
        while (node != null && node.data != data){
            node_p = node;
            if (data > node.data){
                node = node.right;
            }else {
                node = node.left;
            }
        }

        if (node == null){
            return;
        }

        //node有两个节点，找到node右树的最小节点
        if (node.left != null && node.right != null){

            Node minRightNode = node.right;
            //最小节点的父节点
            Node minRightNodeP = node;
            while (null != minRightNode.left){
                minRightNodeP = minRightNode;
                minRightNode = minRightNodeP.left;
            }
            minRightNodeP.left = null;
            node.data = minRightNode.data;
            node = minRightNode;
            node_p =  minRightNodeP;
        }

        //node只有一个子节点或者node没有节点
        Node child;
        if (node.left != null){
              child = node.left;
        }else if (node.right != null){
              child = node.right;
        }else{
            child = null;
        }
        //node为根节点 ，node的节点没有子节点；
        if (node_p == null){
            root = child;
        }else if (node_p.left == node){
            node_p.left = child;
        }else {
            node_p.right = child;
        }



    }

    public static void list(Node p){

        if (p == null){
            return;
        }
        System.out.println(p.data+",");
        list(p.left);
        list(p.right);

    }

    public static void main(String[] args) {

        insert(6);insert(5);insert(3);
        insert(4);insert(7);insert(2);
        list(root);
    }




    static class  Node {
        private int data ;
        private Node left;
        private Node right;


        public Node(int data) {
            this.data = data;
        }
    }


}
