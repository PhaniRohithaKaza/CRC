import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.*;
class CRCServer
{
public static void main(String args[])
{
	int p=0;
Socket          socket   = null; 
ServerSocket    server   = null; 
 DataInputStream in       =  null; 
int[] d=new int[50];
 try
 { 
     server = new ServerSocket(5000); 
     System.out.println("Server started"); 

     System.out.println("Waiting for a client ..."); 

     socket = server.accept(); 
     System.out.println("Client accepted"); 

     in = new DataInputStream( 
         new BufferedInputStream(socket.getInputStream())); 


String line1="";
     while (!(line1= in.readUTF()).equals(Integer.toString(9))) 
     { 
         try
         { 

          d[p++]=Integer.parseInt(line1);
             
         } 
         catch(Exception ex) 
         { 
             System.out.println(ex); 
         } 
  
     } 
     System.out.println("Closing connection"); 

     socket.close(); 
     in.close(); 
 } 
 catch(IOException ex) 
 { 
     System.out.println(ex); 
 } 
 Scanner sc=new Scanner(System.in);
 int m,g[],n,z[],r[],rm,i,j,k;
 n=p;
System.out.print("Enter no. of generator bits :");
k=sc.nextInt();
int[] d1=new int[n+k];
g=new int[k];
System.out.print("Enter generator bits :");
for(j=0;j<k;j++)
g[j]=sc.nextInt();
r=new int[k+n];
for(i=0;i<k;i++)
r[i]=d[i];
z=new int[k];
for(i=0;i<k;i++)
z[i]=0;
int k1=0;
for(i=0;i<n;i++)
{
k1=0;
rm=r[i];
for(j=i;j<k+i;j++)
{
if(rm==0)
r[j]=xor(r[j],z[k1]);
else
r[j]=xor(r[j],g[k1]);
k1++;
}
r[k+i]=d[k+i];
}

int s=0;
for(i=n;i<n+k-1;i++)
{
if(r[i]!=0)
{s=1;
System.out.println("Error detected");
break;
}
}
if(s==0)
	System.out.println("No Error detected");
	
}
public static int xor(int x,int y)
{
if(x==y)
return(0);
else
return(1);
}
}