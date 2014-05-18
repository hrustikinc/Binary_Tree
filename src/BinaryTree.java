import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BinaryTree extends JPanel
{
    private static int HEIGHT;
    public DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
    private BinaryTreeModel binaryTreeModel;
    private ArrayList<NodeImg> nodeImgArray = new ArrayList<NodeImg>();
    private ArrayList<Node> nodeArray = new ArrayList<Node>();

    public BinaryTree(int value)
    {
        binaryTreeModel = new BinaryTreeModel(value);
        setLayout(new GridBagLayout());
        init();
    }

    private void init()
    {
        HEIGHT = binaryTreeModel.getHeight();
        nodeImgArray.clear();
        nodeArray.clear();
        removeAll();
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < Math.pow(2, i); j++)
            {
                ArrayList<String> link = new ArrayList<String>();
                for (int k = 0; k < i; k++)
                {
                    int n = (int) Math.pow(2, i) / (int) Math.pow(2, k + 1);
                    if ((j/n) % 2 == 0)
                    {
                        link.add("left");
                    } else
                    {
                        link.add("right");
                    }
                }
                Node node = binaryTreeModel.search(link);
                int nodeValue;
                if (node == null)
                {
                    nodeValue = 0;
                } else
                {
                    nodeValue = node.getValue();
                }
                NodeImg nodeImg;
                if (nodeValue != 0)
                {
                    nodeImg = new NodeImg(nodeValue, node.getDate());
                    add(nodeImg,
                            new GBC(j * ((int) Math.pow(2, HEIGHT - 1) / (int) Math.pow(2, i)),
                                    i,
                                    (int) Math.pow(2, HEIGHT - 1) / (int) Math.pow(2, i),
                                    1)
                                    .setInsets(10)
                                    .setAnchor(GridBagConstraints.CENTER));
                } else
                {
                    nodeImg = new NodeImg(nodeValue);
                    add(nodeImg,
                            new GBC(j * ((int) Math.pow(2, HEIGHT - 1) / (int) Math.pow(2, i)),
                                    i,
                                    (int) Math.pow(2, HEIGHT - 1) / (int) Math.pow(2, i),
                                    1)
                                    .setInsets(10)
                                    .setAnchor(GridBagConstraints.CENTER));
                }
                if (nodeValue != 0)
                {
                    nodeImgArray.add(nodeImg);
                    nodeArray.add(node);
                }
            }
        }
        revalidate();
        repaint();
    }

    public void addNewNode(int i)
    {
        binaryTreeModel.addNode(i);
        init();
    }

    public void deleteNode(int i)
    {
        binaryTreeModel.deleteNode(i);
        init();
    }

    public void changesNode(int i1, int i2)
    {
        binaryTreeModel.changesNode(i1, i2);
        init();
    }

    public void balancingTree()
    {
        binaryTreeModel.balancing();
        init();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < nodeArray.size(); i++)
        {
            Node node = nodeArray.get(i);
            if (node != null)
            {
                if (node.getValue() != 0)
                {
                    if (node.getLeftNode().getValue() != 0)
                    {
                        for (int j = 0; j < nodeArray.size(); j++)
                        {
                            if (nodeArray.get(j).equals(node.getLeftNode()))
                            {
                                int x1 = nodeImgArray.get(i).getX();
                                int y1 = nodeImgArray.get(i).getY();
                                int w1 = nodeImgArray.get(i).getWidth();
                                int h1 = nodeImgArray.get(i).getHeight();
                                int x2 = nodeImgArray.get(j).getX();
                                int y2 = nodeImgArray.get(j).getY();
                                int w2 = nodeImgArray.get(j).getWidth();

                                g2.drawLine(x1 + w1/2, y1 + h1, x2 + w2/2, y2);

                                break;
                            }
                        }
                    }
                    if (node.getRightNode().getValue() != 0)
                    {
                        for (int j = 0; j < nodeArray.size(); j++)
                        {
                            if (nodeArray.get(j).equals(node.getRightNode()))
                            {
                                int x1 = nodeImgArray.get(i).getX();
                                int y1 = nodeImgArray.get(i).getY();
                                int w1 = nodeImgArray.get(i).getWidth();
                                int h1 = nodeImgArray.get(i).getHeight();
                                int x2 = nodeImgArray.get(j).getX();
                                int y2 = nodeImgArray.get(j).getY();
                                int w2 = nodeImgArray.get(j).getWidth();

                                g2.drawLine(x1 + w1/2, y1 + h1, x2 + w2/2, y2);

                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public class NodeImg extends JComponent
    {
        private final int value;
        private Date date;

        public NodeImg(int value, Date date)
        {
            this.value = value;
            this.date = date;
            setLayout(new GridBagLayout());

            setBorder(BorderFactory.createLineBorder(new Color(255, 126, 0)));
            add(new JLabel("" + this.value), new GBC(0, 0).setAnchor(GridBagConstraints.CENTER).setInsets(10));
            setToolTipText(dateFormat.format(this.date));
        }

        public NodeImg(int value)
        {
            this.value = value;
            setLayout(new GridBagLayout());

            JLabel label = new JLabel("" + value);
            label.setForeground(new Color(225, 225, 225, 0));
            add((label), new GBC(0, 0).setAnchor(GridBagConstraints.CENTER).setInsets(10));
        }
    }
}