package com.nit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nit.beans.Node;
import com.nit.beans.Packet;
import com.nit.beans.User;
import com.nit.util.DbConnection;

public class UserDAO extends DbConnection{
Connection con=null;
boolean flag=false;
PreparedStatement pstmt=null,pst=null;
List<Integer> sumdata=new ArrayList<Integer>();
List<Integer> sumdata1=new ArrayList<Integer>();
	public UserDAO() {
	con=getConnection();
	}
	
	
	
	public List<Node> getFailedNodes()
	{
		List<Node> list=new ArrayList<Node>();
		int i=0;
				try
				{
					pst=con.prepareStatement("select * from nodes where status=1");
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						Node n=new Node();
						n.setDest(rs.getString(2));
						n.setSrc(rs.getString(1));
						list.add(n);
						
					}
					 i=pst.executeUpdate();
		pst.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return list;
	}
	
	
	
	
	
	public List<Packet> getReceivedData(String userId)
	{
		List<Packet> list=new ArrayList<Packet>();

				try
				{
					pst=con.prepareStatement("select * from packet where receiver=?");
					pst.setString(1,userId);
					 ResultSet rs=pst.executeQuery();
					 while(rs.next())
					 {
						 Packet p=new Packet();
						 p.setData(rs.getString(4));
						 p.setPackets(rs.getString(5));
						 p.setSender(rs.getString(3));
						 list.add(p);
					 }
					 pst.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return list;
	}
	
	
	
	
	
	public List<String> getAvailablePathsAvail(int id)
	{
		String src="";
		String dest="";
		List<String> data=new ArrayList<String>();
	
		try
		{
		pst=con.prepareStatement("select sender,receiver from packet where id=?");
		pst.setInt(1,id);
		ResultSet et=pst.executeQuery();
		if(et.next())
		{
		src=et.getString(1)	;
		dest=et.getString(2);
		}
		pst.close();
		
		
		
		pstmt=con.prepareStatement("select dest,sweight,dweight from nodes where src=?");
		pstmt.setString(1,src);
		 ResultSet rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 System.out.println("");
			 StringBuffer sb=new StringBuffer(src);
			 int totalsum=0;
			 String node=rs.getString(1);
			 sb.append("-"+node);
			 if(totalsum!=0)
			 totalsum=totalsum+(rs.getInt(2)+rs.getInt(3));
			 else
				 totalsum=totalsum+(rs.getInt(2)+rs.getInt(3));
			 if(node.equals(dest))
			 {
				 data.add(sb.toString());
				 sumdata1.add(totalsum);
			 }
			 else
			 {
			  
				 List<String> strs=new ArrayList<String>();
				 pst=con.prepareStatement("select dest,sweight,dweight from nodes where src=? and dest<>'"+src+"' ");
				 pst.setString(1, node);
				 ResultSet r=pst.executeQuery();
			 
				while (r.next()) {
					 StringBuffer sb1=new StringBuffer();
					int total1=r.getInt(2)+r.getInt(3);
					
					String node1 = r.getString(1);
					
					sb1.append("-" + node1);
					
					if (node1.equals(dest)) {
					
						totalsum=totalsum+total1;
						sb.append(sb1.toString());
						data.add(sb.toString());
						String s=sb.toString().replace(sb1.toString(), "");
						sb.delete(0, sb.length());
						sb.append(s);
						sumdata1.add(totalsum);
						totalsum=totalsum-total1;
						
					}
					else 
					{
						 PreparedStatement p=con.prepareStatement("select dest,sweight,dweight from nodes where src=? and dest not in ('"+node+"','"+node1+"') ");
						 p.setString(1, node1);
						
						 ResultSet rr=p.executeQuery();
						 while(rr.next())
						 {
							 StringBuffer sb2=new StringBuffer();
							 int total2=rr.getInt(2)+rr.getInt(3);
							 String node2=rr.getString(1);
							 sb2.append("-" + node2);
							
							 if(node2.equals(dest))
							 {
								totalsum=totalsum+(total1+total2);
								sb1.append(sb2.toString());
								sb.append(sb1.toString());
								data.add(sb.toString());
								String s=sb.toString().replace(sb1.toString(), "");
								sb.delete(0, sb.length());
								sb.append(s);
								sumdata1.add(totalsum);
								totalsum=totalsum-(total1+total2);
								
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
		return data;
	}
	
	
	public List<Integer> getSumData1()
	{
		return sumdata1;
	}
	
	
	public List<Integer> getSumData()
	{
		return sumdata;
	}
	
	public List<String> getAvailablePaths(int id)
	{
		String src="";
		String dest="";
		List<String> data=new ArrayList<String>();
	
		try
		{
		pst=con.prepareStatement("select sender,receiver from packet where id=?");
		pst.setInt(1,id);
		ResultSet et=pst.executeQuery();
		if(et.next())
		{
		src=et.getString(1)	;
		dest=et.getString(2);
		}
		pst.close();
		
		
		
		pstmt=con.prepareStatement("select dest,sweight,dweight from nodes where src=? and status=0");
		pstmt.setString(1,src);
		 ResultSet rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 System.out.println("");
			 StringBuffer sb=new StringBuffer(src);
			 int totalsum=0;
			 String node=rs.getString(1);
			 sb.append("-"+node);
			 if(totalsum!=0)
			 totalsum=totalsum+(rs.getInt(2)+rs.getInt(3));
			 else
				 totalsum=totalsum+(rs.getInt(2)+rs.getInt(3));
			 if(node.equals(dest))
			 {
				 data.add(sb.toString());
				 sumdata.add(totalsum);
			 }
			 else
			 {
			  
				 List<String> strs=new ArrayList<String>();
				 pst=con.prepareStatement("select dest,sweight,dweight from nodes where src=? and dest<>'"+src+"' and status=0");
				 pst.setString(1, node);
				 ResultSet r=pst.executeQuery();
			 
				while (r.next()) {
					 StringBuffer sb1=new StringBuffer();
					int total1=r.getInt(2)+r.getInt(3);
					
					String node1 = r.getString(1);
					
					sb1.append("-" + node1);
					
					if (node1.equals(dest)) {
					
						totalsum=totalsum+total1;
						sb.append(sb1.toString());
						data.add(sb.toString());
						String s=sb.toString().replace(sb1.toString(), "");
						sb.delete(0, sb.length());
						sb.append(s);
						sumdata.add(totalsum);
						totalsum=totalsum-total1;
						
					}
					else 
					{
						 PreparedStatement p=con.prepareStatement("select dest,sweight,dweight from nodes where src=? and dest not in ('"+node+"','"+node1+"') and status=0");
						 p.setString(1, node1);
						
						 ResultSet rr=p.executeQuery();
						 while(rr.next())
						 {
							 StringBuffer sb2=new StringBuffer();
							 int total2=rr.getInt(2)+rr.getInt(3);
							 String node2=rr.getString(1);
							 sb2.append("-" + node2);
							
							 if(node2.equals(dest))
							 {
								 totalsum=totalsum+(total1+total2);
								sb1.append(sb2.toString());
								sb.append(sb1.toString());
								data.add(sb.toString());
								String s=sb.toString().replace(sb1.toString(), "");
								sb.delete(0, sb.length());
								sb.append(s);
								sumdata.add(totalsum);
								totalsum=totalsum-(total1+total2);
								
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
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	public List<Packet> getData()
	{
		List<Packet> data=new ArrayList<Packet>();
				try
				{
					pst=con.prepareStatement("select * from packet");
					 ResultSet rs=pst.executeQuery();
					 while(rs.next())
					 {
						 Packet p=new Packet();
						 p.setData(rs.getString(4));
						 p.setId(rs.getInt(1));
						 p.setPackets(rs.getString(5));
						 p.setReceiver(rs.getString(2));
						 p.setSender(rs.getString(3));
						 data.add(p);
					 }
					 pst.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return data;
	}
	
	public User loginCheck(User regbean) {
		String loginid = regbean.getUserId();
		String password = regbean.getPassword();
		User regbean1 = new User();
		try {
			con = getConnection();
			pst = con
					.prepareStatement("select role from users where userId=? and password=?");
			pst.setString(1, loginid);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				regbean1.setRole(rs.getString(1));
				
			} else {
				flag = false;
				regbean1.setRole("");
				regbean1.setUserId("");
				
			}
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			regbean1.setRole("");
			
			flag = false;
		} 

		return regbean1;
	}


	public int sendData(Packet p) {
		int i=0;
		try
		{
			pst=con.prepareStatement("insert into packet values((SELECT NVL(MAX(id),0)+1 FROM packet),?,?,?,?)");
			
			pst.setString(1,p.getReceiver());
			pst.setString(2,p.getSender());
			pst.setString(3,p.getData());
			pst.setString(4,p.getPackets());
			 i=pst.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}


	public int setNodeFailed(String src, String dest) {
		int i=0;
				try
				{
					pst=con.prepareStatement("update nodes  set status=1 where src=? and dest=?");
					pst.setString(1,src);
					pst.setString(2,dest);
					 i=pst.executeUpdate();
					 pst.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return i;
	}



	public int recoverNode(String src, String dest) {
		int i=0;
		try
		{
			pst=con.prepareStatement("update nodes  set status=0 where src=? and dest=?");
			pst.setString(1,src);
			pst.setString(2,dest);
			 i=pst.executeUpdate();
			 pst.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;

	}


	
	
	
	
}
