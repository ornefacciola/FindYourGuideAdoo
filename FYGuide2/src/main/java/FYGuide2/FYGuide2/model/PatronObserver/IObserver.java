package FYGuide2.FYGuide2.model.PatronObserver;

import FYGuide2.FYGuide2.model.User;

public interface IObserver {
    String notificar(User user, int rol);
}
