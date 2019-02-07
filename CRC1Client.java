import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.lang.*;
class CRC1Client
{
public static void main(String args[])
{
	 Socket socket            = null; 
	    DataInputStream  input   = null; 
	     DataOutputStream out     = null; 
	     try
	     { 
	         socket = new Socket("localhost",5000); 
	         System.out.println("Connected"); 


	         input  = new DataInputStream(System.in);  
	         out    = new DataOutputStream(socket.getOutputStream()); 
	     } 
	     catch(UnknownHostException u) 
	     { 
	         System.out.println(u); 
	     } 
	     catch(IOException i) 
	     { 
	         System.out.println(i); 
	     } 

Scanner sc=new Scanner(System.in);
int i=0,j=0,n=0,k=0;
System.out.println("Enter no.of data bits:");
n=sc.nextInt();
System.out.println("Enter no.of CRC generator bits:");
k=sc.nextInt();
int[] d=new int[n];
int[] gen=new int[k];
System.out.println("Enter data bits:");
for(i=0;i<n;i++)
	d[i]=sc.nextInt();
System.out.println("Enter generator bits:");
for(i=0;i<k;i++)
	gen[i]=sc.nextInt();
int[] crc=new int[k-1];
int[] f=new int[n+k];
for(i=0;i<n;i++)
	f[i]=d[i];
for(i=0;i<k-1;i++)
	f[n+i]=0;
int[] r=new int[k+n];
for(i=0;i<k;i++)
r[i]=f[i];
int[] z=new int[k];
for(i=0;i<k;i++)
z[i]=0;
int k1=0,rm;
for(i=0;i<n;i++)
{
k1=0;
rm=r[i];
for(j=i;j<k+i;j++)
{
if(rm==0)
r[j]=xor(r[j],z[k1]);
else
r[j]=xor(r[j],gen[k1]);
k1++;
}
r[k+i]=f[k+i];
}
System.out.print("The crc bits added are :");
for(i=n;i<n+k-1;i++)
{
f[i]=r[i];
System.out.print(f[i]);
}
System.out.print("\nThe code data is :");
for(i=0;i<n+k-1;i++)
{
System.out.print(f[i]);
}

for(int p=0;p<n+k-1;p++)
{
	 try
{ 
    out.writeUTF(Integer.toString(f[p])); 
} 
catch(IOException ex) 
{ 
    System.out.println(ex); 
} 
}

try
{ 

out.writeUTF(Integer.toString(9)); 
} 
catch(IOException ex) 
{ 
System.out.println(ex); 
} 
try
{ 
input.close(); 
out.close(); 
socket.close(); 
} 
catch(IOException ex) 
{ 
System.out.println(ex); 
} 




}
public static int xor(int x,int y)
{
if(x==y)
return(0);
else
return(1);
}
}