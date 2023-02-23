import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cursor_ {
    static JFrame frame = new JFrame();//создаем форму
    static JPanel panel = new JPanel();//создаем панель

    public static void changeCursor(MouseEvent e){//метод изменения курсора
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public static void defaultCursor(MouseEvent e){//метод возврата курсора к дефолтному
        panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Изменения курсора мыши");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width = 400, height = 400;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        panel.setFocusable(true);//делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
        frame.add(panel);//добавляем панель на форму
        panel.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на панель
            public void mouseEntered(MouseEvent e) {//если мышь зашла на панель, то меняем курсор
                changeCursor(e);
            }
            public void mouseExited(MouseEvent e) {//если мышь вышла с панели, то курсор дефолтный
                defaultCursor(e);
            }
        });
        frame.setVisible(true);//делаем форму видимой
    }
}
