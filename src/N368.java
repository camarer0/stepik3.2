import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class N368{
    static JFrame frame = new JFrame();//создаем форму
    static JTextField textBox = new JTextField();//создаем текстбокс
    static String n1="",n2="",op="";//первое число, второе число, операция

    private static double calculate() {//метод вычисления
        return switch (op) {//обрабатываем знак операции и возвращаем результат
            case "+" -> Double.parseDouble(n1) + Double.parseDouble(n2);
            case "-" -> Double.parseDouble(n1) - Double.parseDouble(n2);
            case "*" -> Double.parseDouble(n1) * Double.parseDouble(n2);
            case "/" -> Double.parseDouble(n1) / Double.parseDouble(n2);
            default -> 0;
        };
    }

    public static void main(String[] args) {

        frame.setLayout(new BorderLayout());//задаем менеджер расположения объектов, чтобы потом добавить 2 панели
        frame.setResizable(false);//отключаем возможность изменения размеров
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Калькулятор");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=400, height=250;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна

        JPanel panel1 = new JPanel (), panel2 = new JPanel ();//создаем 2 панели
        panel1.setLayout(new BorderLayout());//устанавливаем им менеджеров расположения
        panel2.setLayout(new GridLayout(4,4,1,1));
        panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//устанавливаем им границы
        panel2.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        textBox.setEnabled(false);//отключаем у него ввод
        textBox.setFont(new Font("TimesRoman", Font.PLAIN, 20));//меняем шрифт
        textBox.setDisabledTextColor(Color.BLACK);//меняем цвет шрифта отключенного текстбокса, чтобы его было лучше видно
        panel1.add(textBox);//добавляем на первую панель

        var buttons = new ArrayList<JButton>();//создаем список для хранения кнопок
        String [] nameButtons = new String[] {"1","2","3","+","4","5","6","-","7","8","9","*","C","0","=","/"};//создаем массив с названиями кнопок
        Font fontButton = new Font("TimesRoman", Font.BOLD, 35);//указываем шрифт для кнопок
        for (String name: nameButtons) {
            buttons.add(new JButton(name));//создаем кнопки и добавляем их в список
            JButton newButton =  buttons.get(buttons.size() - 1);//вытаскиваем название объекта кнопки
            newButton.setFont(fontButton);//меняем шрифт
            panel2.add(newButton);//добавляем на вторую панель
            //добавляем слушателя на кнопку
            newButton.addActionListener(e -> {
                String text = textBox.getText();//для удобства сохраним в переменнные
                String b = newButton.getText();
                switch (b) {
                    case "C"://обнуляемся
                        textBox.setText("");
                        n1="";
                        n2="";
                        op="";
                        break;
                    case "=":
                        if (n1.length()>0&&n2.length()>0&&op.length()>0){//если есть данные для расчета
                            double result = calculate();//получаем результат
                            textBox.setText(result%1==0?Integer.toString((int) result): String.valueOf(result));//переводим в строку, и если число целое, то без дробной части, и отображаем
                            n1=textBox.getText();//после вычисления полученное значение пишем в n1, чтобы сразу можно было продолжить дальнейшее вычисление, остальное обнуляем
                            n2="";
                            op="";
                        }
                        break;
                    case "+","-","*","/":
                        if (n1.length() > 0&&op.length() == 0){//если есть первое число, но нет знака, то пишем знак
                            textBox.setText(text+b);
                            op=b;
                        }
                        break;
                    case "0":
                        if (op.equals("/")&&n2.length() == 0)//если деление на ноль то выходим
                            break;
                    default:
                        if (op.length() == 0 & !n1.equals("0")) {//если вводим цифру до знака и перед ней нет нуля
                            n1 = n1 + b;//обновляем первое число
                            textBox.setText(text+b);//обновляем текстбокс
                        }
                        if (op.length() != 0 & !n2.equals("0")) {//если вводим цифры после знака и перед ней нет нуля
                            n2 = n2 + b;//обновляем второе число
                            textBox.setText(text+b);//обновляем текстбокс
                        }
                }
            });
        }

        frame.add(panel1,BorderLayout.NORTH);//добавляем панели на форму
        frame.add(panel2,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);//делаем форму видимой
    }
}
