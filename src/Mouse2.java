import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Mouse2{
    static JFrame frame = new JFrame();//создаем форму
    static JLayeredPane panel = new JLayeredPane();//создаем многослойную панель

    public static void add(MouseEvent e){//метод добавления объекта по клику
        if (e.getButton()==1){//если кнопка левая
            JLabel label = new JLabel("X:"+e.getX()+" Y:"+e.getY());//создаем метку и указываем текст
            label.setBounds(e.getX(),e.getY(),100,20);//устанавливаем местоположенеие
            label.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на метку
                public void mouseClicked(MouseEvent e) {
                    delete(e);
                }
            });
            panel.add(label);//добаавляем метку на панель
        }
    }

    public static void delete(MouseEvent e) {//метод удаления объекта по клику колесика
        if (e.getButton()==2){//если кнопка колесико
            panel.remove((JLabel) e.getSource());//получаем объект, вызвавший событие, кастим его в JLabel и удаляем из панели
            panel.repaint();//обязательно обновляем панель, иначе изменения не отобразятся
        }
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Добавление и удаление мышкой");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width = 400, height = 400;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        panel.setFocusable(true);//делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
        frame.add(panel);//добавляем панель на форму
        panel.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на панель
            public void mouseClicked(MouseEvent e) {
                add(e);
            }
        });
        frame.setVisible(true);//делаем форму видимой
    }
}
