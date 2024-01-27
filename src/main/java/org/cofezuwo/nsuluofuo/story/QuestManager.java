package org.cofezuwo.nsuluofuo.story;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.utils.Utils;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class QuestManager {

	private Gson gson;

	private Quest questTree;

	@Getter
	@Setter
	private boolean active;

	private List<Quest> notAvailableQuests;
	private List<Quest> completedQuests;
	private List<Quest> activeQuests;
	private List<Quest> tmp;

	public QuestManager() {
		this.gson = new Gson();

		this.notAvailableQuests = new ArrayList<>();
		this.completedQuests = new ArrayList<>();
		this.activeQuests = new ArrayList<>();

		this.tmp = new ArrayList<>();

		parseQuestFile();
		buildQuestLists(questTree);
	}

	public void update() {
		if(!this.isActive()) return;

		tmp.clear();
		tmp.addAll(this.activeQuests);


		for(Quest quest: this.tmp) {

			if(quest.checkConditions()) {
				quest.doActions();
				quest.setStatus(QuestStatus.DONE);
				for(Quest child : quest.getChildren()) {
					child.setStatus(QuestStatus.ACTIVE);
				}
				clearQuestLists();
				buildQuestLists(questTree);
			}
		}

	}

	private void clearQuestLists() {
		this.notAvailableQuests.clear();
		this.completedQuests.clear();
		this.activeQuests.clear();
	}

	public void render(Graphics g) {
		if(!this.isActive()) return;

	}

	public void buildQuestLists(Quest quest) {
		switch(quest.getStatus()) {
			case NA: this.notAvailableQuests.add(quest); break;
			case ACTIVE: this.activeQuests.add(quest); break;
			case DONE: this.completedQuests.add(quest); break;
			default: this.notAvailableQuests.add(quest);
		}

		if(null != quest.getChildren()) {
			for(Quest q : quest.getChildren()) {
				buildQuestLists(q);
			}
		}
	}

	public void parseQuestFile() {
		this.questTree = gson.fromJson(Utils.loadFileAsString("quests/quests.json"), Quest.class);
	}

	public void printQuests(Quest q) {
		System.out.println(q.toString());
	}

	public String getActiveQuests() {
		StringBuilder sb = new StringBuilder();

		for(Quest q : this.activeQuests) {
			sb.append(q.getName()).append("\n");
		}

		return sb.toString();
	}

	public String getNotAvailableQuests() {
		StringBuilder sb = new StringBuilder();

		for(Quest q : this.notAvailableQuests) {
			sb.append(q.getName()).append("\n");
		}

		return sb.toString();
	}

	public String getDoneQuests() {
		StringBuilder sb = new StringBuilder();

		for(Quest q : this.completedQuests) {
			sb.append(q.getName()).append("\n");
		}

		return sb.toString();
	}
}
