package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NewRoundStateTest {
    static class FigureLocationMock implements InterfaceFigureLocation {
        private boolean newTurn;
        FigureLocationMock(boolean newTurn){
            this.newTurn = newTurn;
        }


        @Override
        public boolean placeFigures(PlayerOrder player, int figureCount) {
            return false;
        }

        @Override
        public HasAction tryToPlaceFigures(PlayerOrder player, int count) {
            return null;
        }

        @Override
        public ActionResult makeAction(PlayerOrder player, Collection<Effect> inputResources,
                                       Collection<Effect> outputResources) {
            return null;
        }

        @Override
        public boolean skipAction(PlayerOrder player) {
            return false;
        }

        @Override
        public HasAction tryToMakeAction(PlayerOrder player) {
            return null;
        }

        @Override
        public boolean newTurn() {
            return newTurn;
        }
    }
    static class NewRoundMock implements InterfaceNewTurn{
        boolean newTurn = false;
        @Override
        public void newTurn() {
            newTurn = true;
        }
    }

    @Test
    public void tryToMakeAutomaticActionTest(){
        List<InterfaceFigureLocation> places1 = new ArrayList<>();
        places1.add(new FigureLocationMock(true));

        List<InterfaceFigureLocation> places2 = new ArrayList<>();
        places2.add(new FigureLocationMock(false));

        NewRoundState nrs1 = new NewRoundState(places1, new NewRoundMock());
        assertEquals(nrs1.tryToMakeAutomaticAction(new PlayerOrder(1,1)), HasAction.NO_ACTION_POSSIBLE);
        NewRoundState nrs2 = new NewRoundState(places2, new NewRoundMock());
        assertEquals(nrs2.tryToMakeAutomaticAction(new PlayerOrder(1,1)), HasAction.AUTOMATIC_ACTION_DONE);
    }

}