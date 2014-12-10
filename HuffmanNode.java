public class HuffmanNode implements Comparable<HuffmanNode>
{
   private int f;
   private int c;
   private HuffmanNode l;
   private HuffmanNode r;
   public HuffmanNode(int ascii, int frequency, HuffmanNode left, HuffmanNode right)
   {
      f=frequency;
      c=ascii;
      l=left;
      r=right;
   }
   public int compareTo(HuffmanNode other)
   {
      return f-other.getFrequency();
   }
   public int getAscii()
   {
      return c;
   }
   public int getFrequency()
   {
      return f;
   }
   public HuffmanNode getLeft()
   {
      return l;
   }
   public HuffmanNode getRight()
   {
      return r;
   }
   public void setLeft(HuffmanNode n)
   {
      l=n;
   }
   public void setRight(HuffmanNode n)
   {
      r=n;
   }
}