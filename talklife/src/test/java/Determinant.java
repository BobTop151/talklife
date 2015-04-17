import java.util.ArrayList;
import java.util.List;

public class Determinant {

	/**
	 * 计算二阶行列式
	 * 
	 * @param list
	 * @return
	 */
	public double second_order(List<List> list) {
		double result = (Double) list.get(0).get(0)
				* (Double) list.get(1).get(1) - (Double) list.get(0).get(1)
				* (Double) list.get(1).get(0);
		return result;
	}

	public double third_order(List<List> list) {
		List<Double> line0 = list.get(0);
		List<Double> line1 = list.get(1);
		List<Double> line2 = list.get(2);
		System.out.println(line0.get(0) + " " + line0.get(1) + " " + line0.get(2));
		System.out.println(line1.get(0) + " " + line1.get(1) + " " + line1.get(2));
		System.out.println(line2.get(0) + " " + line2.get(1) + " " + line2.get(2));
		// list.remove(0);
		double result = 0;
		List temp0 = new ArrayList();
		temp0.add(list.get(0));
		list.removeAll(temp0);
		// step1：循环之前削去list的第一个元素，然后将list赋值给另一个集合
		// step2：每次循环时去掉第一步得到的集合其他元素对应位置的子元素
		for (int i = 0; i < list.size(); i++) {// 循环次数为list的元素个数
			List temp1 = new ArrayList();
			for (int j = 0; j < list.size()-1; j++) {// 循环次数为list的元素个数减一
				double son = 0;
				son = (Double) list.get(j).get(i);
				temp1.add(son);
				list.get(j)
			}
			
			
//			result += (Double)list.get(0).get(i)* Math.pow(-1, 1 + i + 1) * second_order(a);
		}
		return 0;

	}

	public static void main(String[] args) {

		List<Double> li = new ArrayList<Double>();
		List<Double> li2 = new ArrayList<Double>();
		List<Double> li3 = new ArrayList<Double>();
		li.add(1d);
		li.add(3d);
		li.add(8d);
		li2.add(5d);
		li2.add(6d);
		li2.add(9d);
		li3.add(7d);
		li3.add(6d);
		li3.add(3d);
		List<List> list = new ArrayList<List>();
		list.add(li);
		list.add(li2);
		list.add(li3);
		Determinant de = new Determinant();
		System.out.println(de.third_order(list));

	}
}
