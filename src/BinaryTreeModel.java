import java.util.ArrayList;

public class BinaryTreeModel
{
    Node root;
    public BinaryTreeModel(int value)
    {
        this.root = new Node();
        this.root.addNode(value);
    }

    public void addNode(int value)
    {
        root.addNode(value);
        System.out.println("add");
        printNods();
    }

    public void printNods()
    {
        System.out.print(root.print());
        System.out.println();
    }

    public Node search(int value)
    {
        return root.search(getLink(value));
    }

    public Node search(ArrayList<String> link)
    {
        return root.search(link);
    }

    public ArrayList<String> getLink(int value)
    {
        ArrayList<String> arrayListLink = new ArrayList<String>();
        String[] arrayLink = root.getLink(value).split(" ");
        for (int i = 0; i < arrayLink.length; i++)
        {
            if (arrayLink[i] != "")
                arrayListLink.add(arrayLink[i]);
            if (arrayLink[i] == null)
            {
                return null;
            }
        }
        return arrayListLink;
    }

    public void deleteNode(int value)
    {
        root.delete(value);
    }

    public void balancing()
    {
        root.balancing();
    }

    public int getHeight()
    {
        return root.getHeight();
    }

    public void changesNode(int oldValue, int newWalue)
    {
        deleteNode(oldValue);
        addNode(newWalue);
    }
}