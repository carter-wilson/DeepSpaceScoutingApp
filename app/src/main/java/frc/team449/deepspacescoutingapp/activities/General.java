package frc.team449.deepspacescoutingapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import frc.team449.deepspacescoutingapp.R;
import frc.team449.deepspacescoutingapp.activities.base_activites.InmatchBaseActivity;
import frc.team449.deepspacescoutingapp.helpers.SubmitHelper;
import frc.team449.deepspacescoutingapp.model.Match;

public class General extends InmatchBaseActivity {

    private RadioGroup dead;
    private RadioGroup defense;
    private RadioGroup defended;
    private EditText comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_page);

        dead = findViewById(R.id.dead);
        switch (Match.getInstance().getDead()) {
            case -1:
                dead.clearCheck();
                break;
            case 0:
                dead.check(R.id.alive);
                break;
            case 1:
                dead.check(R.id.broken);
                break;
            case 2:
                dead.check(R.id.halfDead);
                break;
            case 3:
                dead.check(R.id.deadAll);
                break;
        }

        defense = findViewById(R.id.defense);
        switch (Match.getInstance().getDefense()) {
            case -1:
                defense.clearCheck();
                break;
            case 0:
                defense.check(R.id.defenseNone);
                break;
            case 1:
                defense.check(R.id.defensePart);
                break;
            case 2:
                defense.check(R.id.defenseAll);
                break;
        }

        defended = findViewById(R.id.defended);
        switch (Match.getInstance().getDefended()) {
            case -1:
                defended.clearCheck();
                break;
            case 0:
                defended.check(R.id.defendedNone);
                break;
            case 1:
                defended.check(R.id.defendedPart);
                break;
            case 2:
                defended.check(R.id.defendedAll);
                break;
        }

        comments = findViewById(R.id.comments);
        comments.setText(Match.getInstance().getComments());
    }

    @Override
    protected void setupNavButtons() {
        prevButton.setText("< Endgame");
        nextButton.setText("Submit");
        prevActivity = Endgame.class;
    }

    @Override
    protected void saveData(){
        switch (dead.getCheckedRadioButtonId()) {
            case R.id.alive:
                Match.getInstance().setDead(0);
                break;
            case R.id.broken:
                Match.getInstance().setDead(1);
                break;
            case R.id.halfDead:
                Match.getInstance().setDead(2);
                break;
            case R.id.deadAll:
                Match.getInstance().setDead(3);
                break;
            default:
                Match.getInstance().setDead(-1);
                break;
        }

        switch (defense.getCheckedRadioButtonId()) {
            case R.id.defenseNone:
                Match.getInstance().setDefense(0);
                break;
            case R.id.defensePart:
                Match.getInstance().setDefense(1);
                break;
            case R.id.defenseAll:
                Match.getInstance().setDefense(2);
                break;
            default:
                Match.getInstance().setDefense(-1);
                break;
        }

        switch (defended.getCheckedRadioButtonId()) {
            case R.id.defendedNone:
                Match.getInstance().setDefended(0);
                break;
            case R.id.defendedPart:
                Match.getInstance().setDefended(1);
                break;
            case R.id.defendedAll:
                Match.getInstance().setDefended(2);
                break;
            default:
                Match.getInstance().setDefended(-1);
                break;
        }

        Match.getInstance().setComments(comments.getText().toString());
    }

    @Override
    public void toNext(View v) {
        saveData();

        // Submit
        SubmitHelper.submit(this);
    }

}
