package ddd.model;

import java.io.Serializable;

public class GeneralFeature implements Feature, Serializable{

	public @interface Info {

		String category() default "";

		String name() default "";

		String displayName() default "";

		String shortDescription() default "";

		String longDescription() default "";

		String helpId() default "";

		String smallIcon() default "";

		String largeIcon() default "";

		boolean expert() default false;

		boolean hidden() default false;

		boolean preferred() default false;

	}

	private String category;

	private String name;

	private String displayName;

	private boolean isExpert;

	private boolean isHidden;

	private boolean isPreferred;

	private String shortDescription;

	private String longDescription;

	private String smallIcon;

	private String largeIcon;

	private String helpId;

	private static final long serialVersionUID = -527056249660242307L;


	/**
	 * the category of this feature, used to organize the features into groups.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * the programmatic name of this feature
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the localized display name of this feature.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * The "expert" flag is used to distinguish between those features that are
	 * intended for expert users from those that are intended for normal users.
	 */
	public boolean isExpert() {
		return isExpert;
	}

	/**
	 * The "hidden" flag is used to identify features that are intended only for
	 * tool use, and which should not be exposed to humans.
	 */
	public boolean isHidden() {
		return isHidden;
	}

	/**
	 * The "preferred" flag is used to identify features that are particularly
	 * important for presenting to humans.
	 */
	public boolean isPreferred() {
		return isPreferred;
	}

	/**
	 * the short description of this feature, i18n
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * the long description of this feature. i18n
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * the helpId of this feature
	 */
	public String getHelpId() {
		return helpId;
	}

	/**
	 * the small icon path of this feature
	 */
	public String getSmallIcon() {
		return smallIcon;
	}

	/**
	 * the large icon path of this feature
	 */
	public String getLargeIcon() {
		return largeIcon;
	}

}
