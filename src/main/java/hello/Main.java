package hello;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hello");

    public static void main(String[] args) {
        Message childMessage = createMessage("Child message!", null);
        Message parentMessage = createMessage("Parent message!", childMessage);

        ENTITY_MANAGER_FACTORY.close();
    }

    public static Message createMessage(String text, Message nextMessage) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Message message = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            message = new Message();
            message.setText(text);

            if (nextMessage != null) {
                message.setNextMessage(nextMessage);
            }

            manager.persist(message);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }

            ex.printStackTrace();
        } finally {
            manager.close();
        }

        return message;
    }
}
