package com.nit.beans;

import java.util.ArrayList;
import java.util.List;

public class OutsideExam {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("abcba");
		list.add("abcd");
	

		List<Integer> listd = mystery(list);
		//System.out.println(listd.size());
		for (Integer i : listd) {
			System.out.println(i);
		}

	}

	public static List<Integer> mystery(List<String> letter) {
		List<Integer> list = new ArrayList<Integer>();
		// Write your code here
		
		for (String word : letter) {
		
		
			StringBuffer sb = new StringBuffer(word);

			if (word.equals(sb.reverse().toString())) {
				list.add(0);

			} else {
				char[] ch = word.toCharArray();
				int	count=0;
				outer:
				for (int i = ch.length - 1; i >= 0; i--) {
					
					int no = ch[i];
					
					for (int j = no; j >= 97; j--) {
						
						if (j == 97) {
							ch[i] = (char) j;
						
						} else {
							ch[i] = (char) no--;
						}

						String s = new String(ch);
						System.out.println(s);
						StringBuffer sb1 = new StringBuffer(s);
						//System.out.println(count);
						if (sb.toString().equals(sb1.reverse().toString())) {
							// System.out.println(count);
							list.add(count);
							break outer;
						}
						
						count++;
					}
					count--;

				}
			}

		}
		//return list;
		return list;
	}

}
