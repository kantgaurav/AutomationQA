package pages

import core.BaseSteps
import core.ControlFactory

public class DocuSignPageSteps {
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}
}
