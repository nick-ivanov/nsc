/*
    Network Signal Coder -- a utility that converts binary
        sequences into standard network physical signals.

    Copyright (C) 2015-2017  Nick Ivanov <nnrowan@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package nsc;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.StatusBar;

class RootContainer extends BorderPane {

    RootContainer(Stage stage) {
        super();
        Stage stage1 = stage;

        StatusBar statusBar = new StatusBar();
        CenterContainer centerContainer = new CenterContainer(statusBar);
        TopContainer topContainer = new TopContainer(stage1, centerContainer);
        BitBox bitBox = topContainer.getBitBox();

        ScrollPane centerScrollPane = new ScrollPane();
        centerScrollPane.setContent(centerContainer);

        this.setTop(topContainer);
        this.setCenter(centerScrollPane);
        this.setBottom(statusBar);
    }
}
