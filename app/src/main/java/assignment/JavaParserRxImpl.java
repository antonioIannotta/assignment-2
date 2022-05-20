package assignment;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.function.Function;

public class JavaParserRxImpl implements JavaParserApi {

    private Disposable analysisDisposable;
    public JavaParserRxImpl() {
    }

    @Override
    public void getInterfaceReport(String srcInterfacePath) {
        Flowable.fromCallable(() -> new InterfaceReport(srcInterfacePath).getReport())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Override
    public void getClassReport(String srcClassPath) {
        Flowable.fromCallable(() -> new ClassReport(srcClassPath).getReport())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Override
    public void getPackageReport(String srcPackageFolderPath) {
        Flowable.fromCallable(() -> new PackageReport(srcPackageFolderPath).getReport())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Override
    public void getProjectReport(String srcProjectFolderPath) {
        Flowable.fromCallable(() -> new ProjectReport(srcProjectFolderPath).getReport())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Override
    public void analyzeProject(String srcProjectFolderPath, String topic) {
        System.out.println("Not implemented.");
    }

    @Override
    public void analyzeProject(String srcProjectFolderPath, Function callback) {
        this.analysisDisposable = Flowable.fromCallable(() -> new ProjectReport(srcProjectFolderPath).getReport())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(callback::apply, Throwable::printStackTrace);
    }

    @Override
    public void stopAnalyzeProject() {
        if (this.analysisDisposable != null && !this.analysisDisposable.isDisposed())
            this.analysisDisposable.dispose();
    }
}
