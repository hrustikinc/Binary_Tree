
import java.awt.*;

public class GBC extends GridBagConstraints
{
    /**
     * ������� ������ GBC ��������� gridx, gridy
     * �������� ��������� ���������� ����������� �� ���������.
     * @param gridx ������� gridx
     * @param gridy ������� gridy
     */
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     *  ������� ������ GBCf ��������� gridx, gridy,
     *  gridwidth � gridheight. �������� ���������
     *  ���������� ����������� �� ���������.
     *  @param gridx ������� gridx
     *  @param gridy ������� gridy
     *  @param gridwidth ���������� ������ � ����������� �
     *  @param gridheight ���������� ������ � ����������� �
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     *  ������������� �������� anchor.
     *  @param anchor �������� ���������
     *  @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setAnchor (int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     *  ������������� �������� fill.
     *  @param fill �������� ���������
     *  @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     *  ������������� ���� ������.
     *  @param weightx ��� � ����������� �
     *  @param weighty ��� � ����������� �
     *  @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     *  ������������� ������� ���������� ������������ ��� ������.
     *  @param distance ������� �� ���� ������������
     *  @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * ������������� ������� ���������� ������������ ��� ������.
     * @param top ������, ������� ����� ���������� ������������
     * @param left ������ ����� ����� ���0������ ������������
     * @param bottom ������ ������ ����� ���������� ������������
     * @param right ������ ������ ����� ���������� ������������
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     *  ������������� ���������� ����������.
     *  @param ipadx ���������� ���������� � ����������� �
     *  @param ipady ���������� ���������� � ����������� �
     *  @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}