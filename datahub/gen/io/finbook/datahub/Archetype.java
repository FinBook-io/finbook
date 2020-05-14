package io.finbook.datahub;

import java.io.File;
import java.util.Collections;

public class Archetype {
	private final File root;

	public Archetype(File root) {
		this.root = root;
		root.mkdirs();
	}

	public File root() {
		return this.root;
	}

	public Relative relative() {
	    return new Relative(root);
	}

	private static File createParent(File file) {
	    file.getParentFile().mkdirs();
	    return file;
	}

	public Datahub datahub() {
		return new Datahub(root);
	}

	public Datalake datalake() {
		return new Datalake(root);
	}

	public Datamart datamart() {
		return new Datamart(root);
	}

	public static class Datahub {
		private final File root;

		public Datahub(File parent) {
			this.root = new File(parent, "datahub");
			root.mkdirs();
		}

		public File root() {
			return this.root;
		}

		public File stage() {
			return createParent(new File(root, "stage"));
		}

		public File terminal() {
			return createParent(new File(root, "terminals"));
		}
	}

	public static class Datalake {
		private final File root;

		public Datalake(File parent) {
			this.root = new File(parent, "datalake");
			root.mkdirs();
		}

		public File root() {
			return this.root;
		}

		public File eventStore() {
			return createParent(new File(root, "events"));
		}
	}

	public static class Datamart {
		private final File root;

		public Datamart(File parent) {
			this.root = new File(parent, "datamarts");
			root.mkdirs();
		}

		public File root() {
			return this.root;
		}
	}

	public static class Relative {
        private final String name;
        private final File root;

        public Relative(File root) {
            this.name = "";
            this.root = root;
            root.mkdirs();
        }

        public String root() {
            return this.name;
        }

        public Datahub datahub() {
        	return new Datahub(root, name);
        }

        public Datalake datalake() {
        	return new Datalake(root, name);
        }

        public Datamart datamart() {
        	return new Datamart(root, name);
        }

        public static class Datahub {
        	private final File root;
        	private final String name;

        	public Datahub(File parentRoot, String parent) {
        		this.root = new File(parent, "datahub");
        		root.mkdirs();
        		this.name = parent + (!parent.isEmpty() ? "/" : "") + "datahub";
        	}

        	public String root() {
        		return this.name;
        	}

        	public String stage() {
        		return "stage";
        	}

        	public String terminal() {
        		return "terminals";
        	}
        }

        public static class Datalake {
        	private final File root;
        	private final String name;

        	public Datalake(File parentRoot, String parent) {
        		this.root = new File(parent, "datalake");
        		root.mkdirs();
        		this.name = parent + (!parent.isEmpty() ? "/" : "") + "datalake";
        	}

        	public String root() {
        		return this.name;
        	}

        	public String eventStore() {
        		return "events";
        	}
        }

        public static class Datamart {
        	private final File root;
        	private final String name;

        	public Datamart(File parentRoot, String parent) {
        		this.root = new File(parent, "datamarts");
        		root.mkdirs();
        		this.name = parent + (!parent.isEmpty() ? "/" : "") + "datamarts";
        	}

        	public String root() {
        		return this.name;
        	}
        }
	}
}