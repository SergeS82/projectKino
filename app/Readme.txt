Ещё есть несколько особых методов сохранения состояния: onRetainCustomNonConfigurationInstance() и onRetainCustomNonConfigurationInstance(). Скорее всего вы не столкнетесь с этим в обозримом будущем, но иногда про это могут спросить на собеседовании, поэтому вот неплохое разъяснение.
    https://stackoverflow.com/questions/30673331/using-onretaincustomnonconfigurationinstance-to-retain-data-across-configurati/31061177#31061177

Low-Memory
    В прошлых абзацах мы с вами коснулись темы нехватки памяти. Такое случается, когда у вас заканчивается оперативная память и система начинает убивать всё, что не используется прямо сейчас, чтобы отдать тому приложению, которое сейчас на первом плане. Поэтому система начинает закрывать все, что находится в фоновом режиме.
    Когда памяти не хватает, у приложений вызывается коллбэк onLowMemory(), этот метод существует с API 1.0. Но, если приложение и так находится в фоне, то мы даже не узнаем про этот коллбэк, так как приложение будет уничтожено. Поэтому начиная с API 14 есть новый коллбэк — onTrimMemory(level: Int).
    В параметре level нам приходит уровень, насколько сильно система хочет освободить память, он принимает значения от 0 до 100. То есть в этом коллбэке мы можем сами определить логику того, что мы будем высвобождать в зависимости от уровня, чтобы высвободить память и приложение дольше не было уничтожено системой. Это не даёт никаких гарантий, но увеличивает шансы, что приложение дольше сохранит себя.
    Эти методы доступны как для класса Application, так и для остальных компонентов активити, фрагментов и т.д.
Карта жизненного цикла
    OnCreate() > onStart() > onResume() > onPause() > onStop()  > onRestart() -- user navigates to the activity
                                                                                < onStart
                                                                > onDestroy() -- activity shut down
                                                                < onCreate() -- apps with higher priority need memory
                                                    < onResume() -- user returns to the activity
                                                    < omCreate() -- apps with higher priority need memory
Програмное добавление фрагментов
    https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction.html#commitAllowingStateLoss()