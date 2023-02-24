import javax.swing.*;
import java.awt.*;

public class N365{
    static JPanel panel = new JPanel ();//создаем панель

    static void addObject(JComponent o){//метод добавления объектов на панель
        o.setAlignmentX(Component.CENTER_ALIGNMENT);//устанавливаем горизонтальное выравнивание
        panel.add(o);//добавляем объект на панель
        panel.add(Box.createVerticalStrut(10));//добавляем пустой промежуток после него
    };

    public static void main(String[] args) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//задаем менеджер для выравнивания
        panel.add(Box.createVerticalStrut(10));//между компонентами добавляем пустые промежутки

        JFrame frame = new JFrame();//создаем форму
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Комбобокс, чекбокс и текстбокс");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=300, height=200;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна

        JComboBox comboBox = new JComboBox();//создаем комбобокс
        comboBox.addItem("Красный");//добавляем в него значения
        comboBox.addItem("Оранжевый");
        comboBox.addItem("Зеленый");
        comboBox.setMaximumSize(new Dimension(100,30));//задаем ему максимальный размер, чтобы не растягивался по всей форме
        addObject(comboBox);//добавляем его

        JCheckBox checkBox = new JCheckBox("Свой вариант");//создаем чекбокс
        addObject(checkBox);//добавляем его

        JTextField textBox = new JTextField();//создаем текстбокс
        textBox.setEnabled(false);//делаем его недоступным
        textBox.setMaximumSize(new Dimension(100,30));//задаем ему максимальный размер, чтобы не растягивался по всей форме
        addObject(textBox);//добавляем его

        JButton button = new JButton("Ответить");//создаем кнопку
        addObject(button);//добавляем его

        JLabel label = new JLabel("Ответ:");//создаем надпись
        addObject(label);//добавляем ее

        //добавляем слушателя на кнопку
        button.addActionListener(e -> {
            label.setText("Ответ: "+(checkBox.isSelected()?textBox.getText():comboBox.getSelectedItem().toString()));//считываем значение из комбобокса или текстбокса, если чекбокс выбран
        });

        //добавляем слушателя на чекбокс
        checkBox.addActionListener(e -> {
            textBox.setEnabled(checkBox.isSelected());//если чекбокс нажат то текстбокс доступен
            comboBox.setEnabled(!checkBox.isSelected());//если чекбокс нажат то комбобокс недоступен
        });

        frame.add(panel);//добавляем панель на форму
        frame.pack();//пакуем
        frame.setVisible(true);//делаем форму видимой
    }
}
