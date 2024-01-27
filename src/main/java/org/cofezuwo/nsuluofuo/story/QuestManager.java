package org.cofezuwo.nsuluofuo.story;

import java.awt.Graphics;
import java.util.ArrayList;

public class QuestManager {

	private Quest trivel;
	private Quest questss;
	private ArrayList<Quest> quests;
	private static int currentQuest = 0;

	public QuestManager() {
		quests = new ArrayList<Quest>();
		trivel = new Beginning();
		questss = new Quest();
		quests.add(trivel);
		quests.add(questss);
		
	}

	public void update() {
		trivel.update();

		for (int current = currentQuest; current < quests.size(); current++) {
			if (quests.get(current).isDone()) {
				quests.get(current++).setActive(true);
				quests.get(current).update();

				setCurrentQuest(current);
			}

		}

	}

	public void render(Graphics g) {

	}

	public int getCurrentQuest() {
		return currentQuest;
	}

	public void setCurrentQuest(int current) {
		this.currentQuest = current;
	}

}
