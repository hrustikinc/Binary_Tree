import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Node
{
    private int value;
    private Date date;
    private Node leftNode;
    private Node rightNode;

    public void addNode(int value)
    {
        if (this.value == 0)
        {
            this.value = value;
            date = new Date();
            leftNode = new Node();
            rightNode = new Node();
        } else
        {
            if (value <= this.value)
            {
                leftNode.addNode(value);
            } else
            {
                rightNode.addNode(value);
            }
        }
    }

    public void delete(int value)
    {
        if (this.value == 0)
            System.out.println("Даного елемента немає!");
        if (this.value == value)
        {
            if (rightNode.getValue() == 0 && leftNode.getValue() == 0)
            {
                this.value = 0;
                this.date = null;
                this.leftNode = null;
                this.rightNode = null;
            } else
            {
                if (rightNode.getValue() != 0 && leftNode.getValue() != 0)
                {
                    if (rightNode.getLeftNode().getValue() == 0)
                    {
                        this.value = rightNode.getValue();
                        this.date = rightNode.getDate();
                        this.rightNode = rightNode.getRightNode();
                    } else
                    {
                        this.value = rightNode.getExtremeLeftNode().getValue();
                        this.date = rightNode.getExtremeLeftNode().getDate();
                        rightNode.getExtremeLeftNode().delete(rightNode.getExtremeLeftNode().getValue());
                    }
                } else
                {
                    if (leftNode.getValue() == 0)
                    {
                        this.value = rightNode.getValue();
                        this.date = rightNode.getDate();
                        this.leftNode = rightNode.getLeftNode();
                        this.rightNode = rightNode.getRightNode();
                    }
                    if (rightNode.getValue() == 0)
                    {
                        this.value = leftNode.getValue();
                        this.date = leftNode.getDate();
                        this.leftNode = leftNode.getLeftNode();
                        this.rightNode = leftNode.getRightNode();
                    }
                }
            }
        } else
        {
            if (value <= this.value)
            {
                leftNode.delete(value);
            } else
            {
                rightNode.delete(value);
            }
        }
    }

    public Node getExtremeLeftNode()
    {
        if (leftNode.getValue() == 0)
            return this;
        else
            return leftNode.getExtremeLeftNode();
    }

    public String getLink(int value)
    {
        if (this.value == value)
        {
            return "";
        } else
        {
            if (this.value == 0)
            {
                return null;
            } else
            {
                if (value <= this.value)
                {
                    return "left " + leftNode.getLink(value);
                } else
                {
                    return "right " + rightNode.getLink(value);
                }
            }
        }
    }

    public Node search(ArrayList<String> link)
    {
        if (link.size() == 0)
            return this;
        else
        {
            if (link.get(0).equals("left"))
            {
                link.remove(0);
                if (leftNode.getValue() != 0)
                    return leftNode.search(link);
                else
                    return null;
            } else
            {
                link.remove(0);
                if (rightNode.getValue() != 0)
                    return rightNode.search(link);
                else
                    return null;
            }
        }
    }

    public void refactoring(int value, Date date)
    {
        this.value = value;
        this.date = date;
    }

    public boolean balancing()
    {
        if (value == 0)
        {
            return true;
        } else
        {
            if (leftNode.getValue() == 0 && rightNode.getValue() == 0)
            {
                return true;
            } else
            {
                if ((rightNode.getValue() != 0) &&
                        (rightNode.getHeight() - leftNode.getHeight() == 2) &&
                        rightNode.getLeftNode().getHeight() <= rightNode.getRightNode().getHeight())
                {
                    System.out.println("Малое левое вращение " + value);
                    int bufferValue = this.value;
                    Date bufferDate = this.date;
                    this.value = rightNode.getValue();
                    this.date = rightNode.getDate();
                    rightNode.setValue(bufferValue);
                    rightNode.setDate(bufferDate);
                    Node bufferNode = leftNode;
                    leftNode = rightNode;
                    rightNode = leftNode.getRightNode();
                    leftNode.setRightNode(leftNode.getLeftNode());
                    leftNode.setLeftNode(bufferNode);
                    return false;
                }
                if (rightNode.value != 0 &&
                        rightNode.getLeftNode().getValue() != 0 &&
                        rightNode.getHeight() - leftNode.getHeight() == 2 &&
                        rightNode.getLeftNode().getHeight() > rightNode.getRightNode().getHeight())
                {
                    System.out.println("Большое левое вращение " + value);
                    int bufferValue = this.value;
                    Date bufferDate = this.date;
                    Node bufferNode = leftNode;
                    this.value = rightNode.getLeftNode().getValue();
                    this.date = rightNode.getLeftNode().getDate();
                    leftNode = rightNode.getLeftNode();
                    leftNode = rightNode.getLeftNode();
                    leftNode.setValue(bufferValue);
                    leftNode.setDate(bufferDate);
                    rightNode.setLeftNode(leftNode.getRightNode());
                    leftNode.setRightNode(leftNode.getLeftNode());
                    leftNode.setLeftNode(bufferNode);
                    return false;
                }
                if ((leftNode.getValue() != 0) &&
                        (leftNode.getHeight() - rightNode.getHeight() == 2) &&
                        leftNode.getRightNode().getHeight() <= leftNode.getLeftNode().getHeight())
                {
                    System.out.println("Малое правое вращение " + value);
                    int bufferValue = this.value;
                    Date bufferDate = this.date;
                    this.value = leftNode.getValue();
                    this.date = leftNode.getDate();
                    leftNode.setValue(bufferValue);
                    leftNode.setDate(bufferDate);
                    Node bufferNode = rightNode;
                    rightNode = leftNode;
                    leftNode = rightNode.getLeftNode();
                    rightNode.setLeftNode(rightNode.getRightNode());
                    rightNode.setRightNode(bufferNode);
                    return false;
                }
                if (leftNode.value != 0 &&
                        leftNode.getRightNode().getValue() != 0 &&
                        leftNode.getHeight() - rightNode.getHeight() == 2 &&
                        leftNode.getRightNode().getHeight() > leftNode.getLeftNode().getHeight())
                {
                    System.out.println("Большое правое вращение " + value);
                    int bufferValue = this.value;
                    Date bufferDate = this.date;
                    Node bufferNode = rightNode;
                    this.value = leftNode.getRightNode().getValue();
                    this.date = leftNode.getRightNode().getDate();
                    rightNode = leftNode.getRightNode();
                    leftNode.setRightNode(rightNode.getLeftNode());
                    rightNode.setLeftNode(rightNode.getRightNode());
                    rightNode.setRightNode(bufferNode);
                    rightNode.setValue(bufferValue);
                    rightNode.setDate(bufferDate);
                    return false;
                }
                if (!leftNode.balancing())
                   balancing();
                if (!rightNode.balancing())
                    balancing();
                //while (!leftNode.balancing());
                //while (!rightNode.balancing());
                return true;
            }
        }
    }

    public int getHeight()
    {
        if (value == 0)
        {
            return 0;
        }
        if (leftNode.getValue() == 0 && rightNode.getValue() == 0)
        {
            return 1;
        } else
        {
            if (leftNode.getValue() == 0)
            {
                return 1 + rightNode.getHeight();
            }
            if (rightNode.getValue() == 0)
            {
                return 1 + leftNode.getHeight();
            }
            if (leftNode.getValue() != 0 && rightNode.getValue() != 0)
            {
                int leftHeight = leftNode.getHeight();
                int rightHeight = rightNode.getHeight();
                if (leftHeight > rightHeight)
                {
                    return 1 + leftHeight;
                } else
                    return 1 + rightHeight;
            }
        }
        return 0;
    }

    public ArrayList<Node> getNodeArray()
    {
        ArrayList<Node> arrayList = new ArrayList<Node>();
        if (value == 0)
        {
            return arrayList;
        }
        arrayList.add(this);
        arrayList.addAll(leftNode.getNodeArray());
        arrayList.addAll(rightNode.getNodeArray());
        return arrayList;
    }

    public String print()
    {
        if (leftNode.getValue() == 0 && rightNode.getValue() == 0)
        {
            return "" + value;
        }
        String s = value + "(";
        if (leftNode.getValue() != 0)
            s += leftNode.print();
        else
            s += 0;
        s += ", ";
        if (rightNode.getValue() != 0)
            s += rightNode.print();
        else
            s += 0;
        s += ")";
        return s;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}