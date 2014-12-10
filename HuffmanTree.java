import java.util.*;
import java.io.*;
public class HuffmanTree
{
   private HuffmanNode root;
   private boolean DEBUG=false;
   public HuffmanTree(int[] counts)
   {
      HuffmanNode parent;
      HuffmanNode leftchild;
      HuffmanNode rightchild;
      PriorityQueue<HuffmanNode> tree=new PriorityQueue<HuffmanNode>();
      if(DEBUG)
      {
         for (int ii=0; ii<counts.length; ii++)
         {
            System.out.println("index: "+ii+", char: "+(char)ii+", count: "+counts[ii]+";  ");
         }
      }
      for (int ii=0; ii<counts.length; ii++)
      {
         if (counts[ii]!=0)
         {
            tree.add(new HuffmanNode(ii, counts[ii], null, null));
         }
      }
      tree.add(new HuffmanNode(256, 1, null, null));
      while(tree.size()>1)
      {
         leftchild=tree.poll();
         rightchild=tree.poll();
         parent=new HuffmanNode(-1, leftchild.getFrequency()+rightchild.getFrequency(), leftchild, rightchild);
         tree.add(parent);
      }
      root=tree.poll();
   }
      
   public void write(PrintStream output)
   {
      toBits(output, root, "");
   }
   
   private void toBits(PrintStream output, HuffmanNode n, String path)
   {
      if(n.getLeft()==null&n.getRight()==null)
      {
         if(DEBUG)
         {
            System.out.println(n.getAscii()+"\n"+path);
         }
         else
         {
            output.println(n.getAscii()+"\n"+path);
         }
      }
      else
      {
         toBits(output, n.getLeft(), path+0);
         toBits(output, n.getRight(), path+1);
      }
   }
   
   public HuffmanTree(Scanner input)
   {
      String line;
      Scanner linescan;
      int linenum=0;
      int ascii=-1;
      char c;
      root=new HuffmanNode(-1, 0, null, null);
      HuffmanNode previous;
      HuffmanNode current;
      while (input.hasNextLine())
      {
         line=input.nextLine();
         linenum++;
         if(linenum%2==1)
         {
            ascii=new Integer(line);
         }
         else if (linenum%2==0)
         {
            previous=root;
            for (int ii=0; ii<line.length(); ii++)
            {
               c=line.charAt(ii);
               if(c=='0')
               {
                  if(previous.getLeft()==null)
                  {
                     if(ii==line.length()-1)
                     {
                        current=new HuffmanNode(ascii,0, null, null);
                     }
                     else
                     {
                        current=new HuffmanNode(-1, 0, null, null);
                     }
                     previous.setLeft(current);
                  }
                  else
                  {
                     current=previous.getLeft();
                  }
               }
               else
               {
                  if(previous.getRight()==null)
                  {
                     if(ii==line.length()-1)
                     {
                        current=new HuffmanNode(ascii,0, null, null);
                     }
                     else
                     {
                        current=new HuffmanNode(-1, 0, null, null);
                     }
                     previous.setRight(current);
                  }
                  else
                  {
                     current=previous.getRight();
                  }
               }
               previous=current;
            }
         }
      }
   }         
   
   public void decode(BitInputStream input, PrintStream output, int eof)
   {
      int ascii;
      String s="";
      while (true)
      {
         ascii=readBits(input);
         if (ascii<eof)
         {
            s+=(char)ascii;
         }
         else
         {
            break;
         }
      }
      output.print(s);
   }
   
   private int readBits(BitInputStream input)
   {
      int bit;
      HuffmanNode temp=root;
      while (true)
      {
         bit=input.readBit();
         if (bit==0)
         {
            temp=temp.getLeft();
         }
         else
         {
            temp=temp.getRight();
         }
         if (temp.getAscii()>=0)
         {
            return temp.getAscii();
         }
      }
   }
}