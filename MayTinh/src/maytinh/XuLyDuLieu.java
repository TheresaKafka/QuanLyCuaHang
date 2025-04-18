/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh;

import java.util.Stack;

/**
 *
 * @author ACER
 */
public class XuLyDuLieu {
    MayTinh_DB dauvao=new MayTinh_DB();

    public XuLyDuLieu() {
    }
    public XuLyDuLieu(String str)
    {
        dauvao.setDuLieuPhepTinh(str);
    }
    
    public void themDuLieu(String str)
    {
        dauvao.setDuLieuPhepTinh(dauvao.getDuLieuPhepTinh()+str+";");
    }
    public String HienThiDuLieu()
    {
        return dauvao.getDuLieuPhepTinh().replaceAll(";"," ");
    }
     private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    // Hàm thực hiện phép toán
    private double applyOp(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("Lỗi: Chia cho 0!");
                return a / b;
        }
        return 0;
    }

    public void xulytaoketqua() {
        String[] temp = dauvao.getDuLieuPhepTinh().split(";");
        Stack<Double> values = new Stack<>(); // Lưu số
        Stack<Character> operators = new Stack<>(); // Lưu toán tử

        for (String it : temp) {
            it = it.trim(); // Xóa khoảng trắng

            if (it.matches("-?\\d+(\\.\\d+)?")) { // Nếu là số (hỗ trợ cả số âm và số thực)
                values.push(Double.parseDouble(it));
            } else if (it.matches("[+\\-*/]")) { // Nếu là toán tử
                char op = it.charAt(0);
                
                // Xử lý các toán tử trước đó nếu có độ ưu tiên cao hơn hoặc bằng
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(op)) {
                    double b = values.pop();
                    double a = values.pop();
                    char prevOp = operators.pop();
                    values.push(applyOp(a, b, prevOp));
                }
                
                operators.push(op);
            } else {
                throw new IllegalArgumentException("Biểu thức không hợp lệ: " + it);
            }
        }

        // Xử lý phần còn lại trong Stack
        while (!operators.isEmpty()) {
            double b = values.pop();
            double a = values.pop();
            char op = operators.pop();
            values.push(applyOp(a, b, op));
        }
        double ketqua= values.pop();
        themDuLieu("=;"+String.valueOf(ketqua));
    }
}
