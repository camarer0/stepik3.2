import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class N367{
    static JPanel panel = new JPanel ();//создаем панель

    static void addObject(JComponent o){//метод добавления объектов на панель
        o.setAlignmentX(Component.CENTER_ALIGNMENT);//устанавливаем горизонтальное выравнивание
        panel.add(o);//добавляем объект на панель
        panel.add(Box.createVerticalStrut(10));//добавляем пустой промежуток после него, если он задан
    };

    public static void main(String[] args) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//задаем менеджер для выравнивания
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));//расширяем границы панели, чтобы элементы не прижимались вплотную к границе

        JFrame frame = new JFrame();//создаем форму
        frame.setResizable(false);//отключаем возможность изменения ее размеров
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("ТекстАреа");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=300, height=200;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна

        JTextField textBox = new JTextField();//создаем текстбокс
        textBox.setMaximumSize(new Dimension(300,30));//задаем ему максимальный размер, чтобы не растягивался по всей форме
        addObject(textBox);//добавляем его

        JButton button = new JButton("Записать");//создаем кнопку
        addObject(button);//добавляем его

        JTextArea textArea = new JTextArea();//создаем текстареа
        textArea.setRows(4);//задаем ему видимое значение строк, иначе после упаковки он сожмется до одной строки
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//создаем скролл для текстареа
        scroll.setMaximumSize(new Dimension(300,100));//задаем ему максимальный размер, чтобы не растягивался по всей форме
        addObject(scroll);//добавляем его

        //добавляем слушателя на кнопку
        button.addActionListener(e -> {
            textArea.setText(textArea.getText()+(textArea.getText().length()==0?"":"\n")+textBox.getText());//считываем значение из тексбокса в тексареа
        });
        frame.add(panel);//добавляем панель на форму
        //    frame.pack();
        frame.setVisible(true);//делаем форму видимой
    }
}