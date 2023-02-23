import javax.swing.*;
import java.awt.*;

public class RegistrationForm {
    public static void main(String[] args) {
        String login, pass;//переменные для хранения логина и пароля
        JFrame frame = new JFrame();//создаем форму
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Авторизация");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width= 600, height=300;//размер формы
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры и местоположение формы
        JOptionPane pane = new JOptionPane();//создаем диалог
        if (pane.showConfirmDialog(frame, "Будем регистрироваться?", "Вопрос", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0) {//выводим 1 вопрос, если ответ Да, то продолжаем, иначе выходим
            //проверка логина (пока логин не будет более 5 символов и без пробелов - выводим диалог логина)
            while (true) {
                login = pane.showInputDialog(frame, "Введите логин", "Логин", JOptionPane.QUESTION_MESSAGE);
                if (login==null)//если нажата отмена, то выходим из приложения
                    System.exit(0);
                else//иначе делаем проверку логина, и если он нормальный, то выходим из цикла
                    if (login.length()>5&&login.lastIndexOf(" ")==-1) break;
            }
            //первая проверка пароля (пока пароль не будет более 8 символов, без пробелов, хотя бы с 1 цифрой и буквой - выводим диалог пароля)
            JPasswordField pf = new JPasswordField();//создаем поле со звездочками для ввода пароля
            while (true) {
                if (pane.showConfirmDialog(frame, pf, "Введите пароль для логина "+login, JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION) //если нажата отмена, то выходим из приложения
                    System.exit(0);
                else {//если нажата ОК, то проверяем пароль, и если он нормальный, то выходим из цикла
                    pass = new String(pf.getPassword());//сохраняем пароль, сделав строку из символов
                    if (pass.length() > 8 &&//проверка длины
                            pass.lastIndexOf(" ") == -1 &&//проверка пробелов
                            pass.chars().filter(Character::isDigit).count() > 0 &&//проверка на минимум 1 цифру
                            pass.chars().filter(Character::isLetter).count() > 0)//проверка на минимум 1 букву
                        break;
                }
            }
            pf.setText("");//обнуляем пароль в диалоге для ввода нового
            //вторая проверка пароля
            while (true) {
                if (pane.showConfirmDialog(frame, pf, "Повторите пароль для логина "+login, JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION) //если нажата отмена, то выходим из приложения
                    System.exit(0);
                else  //иначе проверяем пароль, и если он совпадает, то выходим из цикла
                    if (new String(pf.getPassword()).equals(pass))//
                        break;
            }
            pane.showMessageDialog(frame,"Поздравляем с успешной регистрацией","Успешная регистрация", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(true);//делаем форму видимой
        }
        else  System.exit(0);//закрываем программу
    }
}
