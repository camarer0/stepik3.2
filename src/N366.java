import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class N366{
    static JPanel panel = new JPanel ();//создаем панель

    static void addObject(JComponent o, int n){//метод добавления объектов на панель
        o.setAlignmentX(Component.LEFT_ALIGNMENT);//устанавливаем горизонтальное выравнивание
        panel.add(o);//добавляем объект на панель
        if (n>0) panel.add(Box.createVerticalStrut(n));//добавляем пустой промежуток после него, если он задан
    };

    public static void main(String[] args) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//задаем менеджер для выравнивания
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));//расширяем границы панели, чтобы элементы не прижимались вплотную к границе

        JFrame frame = new JFrame();//создаем форму
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Радиобаттон");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=300, height=200;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна

        int month = Calendar.getInstance().get(Calendar.MONTH)+1;//вычисляем текущий месяц, чтобы в дальнейшем по умолчанию выставить в радиобаттон текущее время года (месяцы считаются с 0)
        ButtonGroup buttons = new ButtonGroup();//создаем группу кнопок
        JRadioButton dim1 = new JRadioButton("Весна", month > 2 && month < 6);//создаем сами кнопки
        JRadioButton dim2 = new JRadioButton("Лето", month > 5 && month < 9);
        JRadioButton dim3 = new JRadioButton("Осень", month > 8 && month < 12);
        JRadioButton dim4 = new JRadioButton("Зима", month > 11 || month < 3);
        dim1.setActionCommand("Весна");//добавляем для них пометку при нажатии
        dim2.setActionCommand("Лето");
        dim3.setActionCommand("Осень");
        dim4.setActionCommand("Зима");
        buttons.add(dim1);//добавляем кнопки в группу
        buttons.add(dim2);
        buttons.add(dim3);
        buttons.add(dim4);
        addObject(dim1,0);
        addObject(dim2,0);
        addObject(dim3,0);
        addObject(dim4,10);

        JButton button = new JButton("Ответить");//создаем кнопку
        addObject(button,10);//добавляем его

        JLabel label = new JLabel("Ответ:");//создаем надпись
        addObject(label,10);//добавляем ее

        //добавляем слушателя на кнопку
        button.addActionListener(e -> {
            label.setText("Ответ: "+buttons.getSelection().getActionCommand());//считываем значение из радиобаттона
        });

        frame.add(panel);//добавляем панель на форму
        frame.pack();//пакуем
        frame.setVisible(true);//делаем форму видимой
    }
}
