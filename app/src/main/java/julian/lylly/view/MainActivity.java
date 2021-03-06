package julian.lylly.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import julian.lylly.R;
import julian.lylly.model.Organizer;
import julian.lylly.model.OrganizerImpl;
import julian.lylly.model.Prospect;
import julian.lylly.model.Tag;
import julian.lylly.model.Task;

public class MainActivity extends AppCompatActivity {

    private Organizer organizer;
    private int view;
    private TagsView tavi;
    private ProspectsView prvi;
    private ProspectEdit pred;
    private TasksView tasvi;
    private TaskEdit taed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = R.layout.activity_main;
        setContentView(view);

        helper();//TODO:delete
        //create screens:

    }

    public void goToTaskOrganizer() {
        setView(R.layout.activity_task_organizer);
        tasvi = new TasksView(this);
    }

    public void goToProspectOrganizer() {
        setView(R.layout.activity_prospect_organizer);
        prvi = new ProspectsView(this);
    }

    public void goToTagOrganizer() {
        setView(R.layout.activity_tag_organizer);
        tavi = new TagsView(this);
    }

    public void goToProspectEdit(Prospect prospect) {
        setView(R.layout.activity_prospect_edit);
        pred = new ProspectEdit(this, prospect);
    }

    public void goToTaskEdit(Task task) {
        setView(R.layout.activity_task_edit);
        taed = new TaskEdit(this, task);
    }
    
    public void onClickTaskOrganizer(View v) {
        goToTaskOrganizer();
    }

    public void onClickProspectOrganizer(View v) {
        goToProspectOrganizer();
    }

    public void onClickTagOrganizer(View v) {
        goToTagOrganizer();
    }

    public void onBackPressed() {
        switch (view) {
            case (R.layout.activity_task_edit):
                goToTaskOrganizer();
                break;
            case (R.layout.activity_prospect_edit):
                goToProspectOrganizer();
                break;
            case (R.layout.activity_tag_edit):
                goToTagOrganizer();
                break;
            case (R.layout.activity_prospect_organizer):
            case (R.layout.activity_tag_organizer):
            case (R.layout.activity_task_organizer):
                setView(R.layout.activity_main);
                break;
            default:
                super.onBackPressed();
        }
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
        setContentView(view);
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    private void helper() {//TODO:delete
        Tag tag1 = new Tag("du"), tag2 = new Tag("dömel"), tag3 = new Tag("kenguru");
        organizer = new OrganizerImpl();
        organizer.addTag(tag1);
        organizer.addTag(tag2);
        organizer.addTag(tag3);
        organizer.addTag(new Tag("düm düm düüüüm düdüum"));
        List<Integer> weightsPast = new ArrayList<>();
        weightsPast.add(5);
        weightsPast.add(5);
        weightsPast.add(3);
        weightsPast.add(3);
        weightsPast.add(3);
        organizer.addProspect(new Prospect("past", tag1,
                new LocalDate(2015, 10, 10), new LocalDate(2015, 10, 15),
                new Duration(1000 * 60 * 60 * 2), new Duration(1000 * 60 * 60 * 3), weightsPast));
        List<Integer> weightsFuture = new ArrayList<>();
        weightsFuture.add(6);
        weightsFuture.add(6);
        weightsFuture.add(6);
        organizer.addProspect(new Prospect("future", tag2,
                new LocalDate(2016, 1, 20), new LocalDate(2016, 1, 23),
                new Duration(1000 * 60 * 60 * 1), new Duration(1000 * 60 * 30 * 5), weightsFuture));

        Task task1 = new Task();
        task1.setDescr("test task");
        task1.setTag(tag2);
        organizer.addTask(task1);

        Task task2 = new Task();
        task2.setDescr("urg task");
        task2.setUrgency(1);
        organizer.addTask(task2);

        Task task3 = new Task();
        task3.setDescr("done task");
        task3.setTag(tag1);
        task3.setDone(true);
        organizer.addTask(task3);
    }

    public void onClickNewTag(View view) {
        tavi.onClickNewTag();
    }

    public void onClickTagEditOk(View view) {
        tavi.onClickEditOk();
    }

    public void onClickTagEditCancel(View view) {
        tavi.onClickCancel();
    }

    public void onClickNewProspect(View view) {
        goToProspectEdit(null);
    }

    public void onClickProspectEditOk(View view) {
        pred.onClickOk();
    }

    public void onClickProspectEditCancel(View view) {
        pred.onClickCancel();
    }

    public void onClickTaskPlayPause(View view) {
        tasvi.onClickTaskPlayPause(view);
    }

    public void onClickNewTask(View view) {
        goToTaskEdit(null);
    }

    public void onClickTaskEditOk(View view) {
        taed.onClickOk();
    }

    public void onClickTaskEditCancel(View view) {
        taed.onClickCancel();
    }

    public void onClickEditTask(View view) {
        tasvi.onClickEditTask(view);
    }

    public void onClickTaskFinish(View view) {
        tasvi.onClickTaskFinish(view);
    }
}
