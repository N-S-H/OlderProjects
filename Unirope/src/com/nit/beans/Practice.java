package com.nit.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nit.util.DbConnection;
public class Practice 
{ 
  public static void main(String []args) 
  { 
 String src="A";
 String dest="D";

 
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=null,pst=null;
			List<String> data=new ArrayList<String>();
			List<Integer> sumdata=new ArrayList<Integer>();
				try
				{
					pstmt=con.prepareStatement("select dest,sweight,dweight from temp1 where src=? and status=0");
					pstmt.setString(1,src);
					 ResultSet rs=pstmt.executeQuery();
					 int prev=0;
					 while(rs.next())
					 {
						 System.out.println("");
						 StringBuffer sb=new StringBuffer(src);
						 int totalsum=0;
						 String node=rs.getString(1);
						 sb.append("-"+node);
						 System.out.println("prev---"+prev);
						
						 totalsum=(totalsum)*(prev+rs.getInt(2));
						 prev=rs.getInt(2);
						 System.out.println("rs---"+prev);
						 if(node.equals(dest))
						 {
							 data.add(sb.toString());
							 sumdata.add(totalsum);
						 }
						 else
						 {
						  
							 List<String> strs=new ArrayList<String>();
							 pst=con.prepareStatement("select dest,sweight,dweight from temp1 where src=? and  status=0 and dest<>'"+src+"'");
							 pst.setString(1, node);
							 ResultSet r=pst.executeQuery();
						 int prev1=1;
							while (r.next()) {
								 StringBuffer sb1=new StringBuffer();
								 System.out.println("prev1---"+prev1);
								int total1=prev1+r.getInt(2);
								prev1=r.getInt(2);
								System.out.println("r---"+prev1);
								String node1 = r.getString(1);
								
								sb1.append("-" + node1);
								
								if (node1.equals(dest)) {
								
									totalsum=totalsum*total1;
									sb.append(sb1.toString());
									data.add(sb.toString());
									String s=sb.toString().replace(sb1.toString(), "");
									sb.delete(0, sb.length());
									sb.append(s);
									sumdata.add(totalsum);
									totalsum=totalsum/total1;
									
								}
								else 
								{
									 PreparedStatement p=con.prepareStatement("select dest,sweight,dweight from temp1 where src=? and status=0 and dest not in ('"+node+"','"+node1+"') ");
									 p.setString(1, node1);
									
									 ResultSet rr=p.executeQuery();
									 int prev2=0;
									 while(rr.next())
									 {
										 StringBuffer sb2=new StringBuffer();
										 System.out.println("prev2---"+prev2);
										 int total2=prev2+rr.getInt(2);
										 prev2=rr.getInt(2);
										 System.out.println("rr---"+prev2);
										 String node2=rr.getString(1);
										 sb2.append("-" + node2);
										
										 if(node2.equals(dest))
										 {
											totalsum=totalsum*(total1+total2);
											sb1.append(sb2.toString());
											sb.append(sb1.toString());
											data.add(sb.toString());
											String s=sb.toString().replace(sb1.toString(), "");
											sb.delete(0, sb.length());
											sb.append(s);
											sumdata.add(totalsum);
											totalsum=totalsum/(total1+total2);
											
										 }
									 }
									
								}

							}
							 
							
							pst.close();
						 }
						
					 }
					 pstmt.close();
					 
					 
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//return data.size();
				
				for(Integer i:sumdata)
				{
					System.out.println(i);
				}
				
				
					
			
	
  }
  
} 