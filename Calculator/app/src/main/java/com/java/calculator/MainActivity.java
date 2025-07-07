package com.java.calculator;

import static java.nio.file.Files.delete;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import com.java.calculator.R;

class Calculator {
    String expression;
    public Calculator() {
        this.expression = "";
    }
    public static double evaluateExpression(String expr) {
        if (expr == null || expr.isEmpty()) return 0;

        List<Double> numStack = new ArrayList<>();
        List<Character> opStack = new ArrayList<>();

        int i = 0;
        int len = expr.length();

        while (i < len) {
            char ch = expr.charAt(i);

            // 跳过空格
            if (ch == ' ') {
                i++;
                continue;
            }

            // 根号 √
            if (ch == '√') {
                i++; // 跳过 √
                // 解析 √ 后的数字
                StringBuilder sb = new StringBuilder();
                while (i < len && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                if (sb.length() == 0) {
                    throw new IllegalArgumentException("√ 后缺少数字");
                }
                double value = Math.sqrt(Double.parseDouble(sb.toString()));
                numStack.add(value);
                continue; // 跳过 i++，已处理完数字
            }

            // 数字（支持小数）
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < len && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                numStack.add(Double.parseDouble(sb.toString()));
                continue;
            }

            // 运算符：+ - × ÷
            if (ch == '+' || ch == '-' || ch == '×' || ch == '÷') {
                while (!opStack.isEmpty() && precedence(opStack.get(opStack.size() - 1)) >= precedence(ch)) {
                    double b = numStack.remove(numStack.size() - 1);
                    double a = numStack.remove(numStack.size() - 1);
                    char op = opStack.remove(opStack.size() - 1);
                    numStack.add(applyOperator(a, b, op));
                }
                opStack.add(ch);
            } else {
                throw new IllegalArgumentException("不支持的字符: " + ch);
            }

            i++;
        }

        // 剩余运算
        while (!opStack.isEmpty()) {
            double b = numStack.remove(numStack.size() - 1);
            double a = numStack.remove(numStack.size() - 1);
            char op = opStack.remove(opStack.size() - 1);
            numStack.add(applyOperator(a, b, op));
        }

        return numStack.isEmpty() ? 0 : numStack.get(0);
    }

    // 运算符优先级
    private static int precedence(char op) {
        if (op == '×' || op == '÷') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    // 执行运算
    private static double applyOperator(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '×': return a * b;
            case '÷':
                if (b == 0) throw new ArithmeticException("除以零错误");
                return a / b;
            default:
                throw new IllegalArgumentException("无效运算符: " + op);
        }
    }



    public void addExpression(String addEx) {
        expression += addEx;
    }
    public void clearExpression() {
        expression = "";
    }
    public void delete() {
        if (expression.length() == 0) return;
        expression = expression.substring(0, expression.length() - 1);
    }

}


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_backward).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showExpression() {
        TextView textView = findViewById(R.id.expression);
        textView.setText(calculator.expression);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_0) {
            calculator.addExpression("0");
            showExpression();
        } else if (id == R.id.btn_1) {
            calculator.addExpression("1");
            showExpression();
        } else if (id == R.id.btn_2) {
            calculator.addExpression("2");
            showExpression();
        } else if (id == R.id.btn_3) {
            calculator.addExpression("3");
            showExpression();
        } else if (id == R.id.btn_4) {
            calculator.addExpression("4");
            showExpression();
        } else if (id == R.id.btn_5) {
            calculator.addExpression("5");
            showExpression();
        } else if (id == R.id.btn_6) {
            calculator.addExpression("6");
            showExpression();
        } else if (id == R.id.btn_7) {
            calculator.addExpression("7");
            showExpression();
        } else if (id == R.id.btn_8) {
            calculator.addExpression("8");
            showExpression();
        } else if (id == R.id.btn_9) {
            calculator.addExpression("9");
            showExpression();
        } else if (id == R.id.btn_dot) {
            calculator.addExpression(".");
            showExpression();
        } else if (id == R.id.btn_plus) {
            calculator.addExpression("+");
            showExpression();
        } else if (id == R.id.btn_sub) {
            calculator.addExpression("-");
            showExpression();
        } else if (id == R.id.btn_mul) {
            calculator.addExpression("×");
            showExpression();
        } else if (id == R.id.btn_div) {
            calculator.addExpression("÷");
            showExpression();
        } else if (id == R.id.btn_sqrt) {
            calculator.addExpression("√");
            showExpression();
        } else if (id == R.id.btn_backward) {
            calculator.addExpression("1÷"); // 倒数，用户继续输入数字
            showExpression();
        } else if (id == R.id.btn_del) {
            calculator.delete();
            showExpression();
        } else if (id == R.id.btn_c) {
            calculator.clearExpression();
            showExpression();
        } else if (id == R.id.btn_equal) {
            if(!isValidExpression(calculator.expression)) {
                TextView textview = findViewById(R.id.expression);
                textview.setText("错误");
                return;
            }
            double result = calculator.evaluateExpression(calculator.expression);
            long resInt = Math.round(result);
            if(resInt == result) {
                calculator.expression = String.valueOf(resInt);
            }
            else {
                calculator.expression = String.valueOf(result);
            }
            showExpression();
        }
    }

    public static boolean isValidExpression(String expr) {
        if (expr == null || expr.isEmpty()) return false;

        expr = expr.replaceAll("\\s+", ""); // 去除空格

        // 允许的字符集合
        String validChars = "0123456789.+-×÷√";

        // 用于检查合法字符
        for (char ch : expr.toCharArray()) {
            if (validChars.indexOf(ch) == -1) {
                return false; // 出现非法字符
            }
        }

        if (expr.equals("√")) {
            return false;
        }

        // 表达式不能以以下字符开头
        if (expr.startsWith("+") || expr.startsWith("×") || expr.startsWith("÷") || expr.startsWith(".")) {
            return false;
        }

        // 表达式不能以运算符结尾
        if (expr.endsWith("+") || expr.endsWith("-") || expr.endsWith("×") || expr.endsWith("÷") || expr.endsWith(".")) {
            return false;
        }

        // 连续运算符检查（如 ++, ×÷, ..）
        for (int i = 1; i < expr.length(); i++) {
            char prev = expr.charAt(i - 1);
            char curr = expr.charAt(i);

            // 连续两个运算符或两个小数点是非法的
            if (isOperator(prev) && isOperator(curr)) {
                return false;
            }
            if (prev == '.' && curr == '.') {
                return false;
            }

            // 根号后面必须是数字或小数点
            if (prev == '√' && !(Character.isDigit(curr) || curr == '.')) {
                return false;
            }

            // 根号前面不能是数字或小数点
            if (curr == '√' && (Character.isDigit(prev) || prev == '.')) {
                return false;
            }
        }

        // 小数点合法性检查（每个数字部分最多只能一个 .）
        String[] tokens = expr.split("[+\\-×÷√]");
        for (String token : tokens) {
            int dotCount = 0;
            for (char ch : token.toCharArray()) {
                if (ch == '.') dotCount++;
            }
            if (dotCount > 1) {
                return false;
            }
        }

        return true;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '×' || ch == '÷';
    }


}