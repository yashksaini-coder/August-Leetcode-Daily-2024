import fetch from "node-fetch";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const toc = async () => {
  // Fetch from API
  const data = await fetch(
    "https://api.github.com/repos/yashksaini-coder/August-Leetcode-Daily-2024/contents?ref=main"
  )
    .then((response) => response.json())
    .then((data) => data);
  
  var arr = Object.values(data);
  
  // Filter out the folders
  const folders = arr.filter(
    (item) => item.type === "dir" && item.name[0] !== "."
  );
  
  // Make a table of contents
  var toc = [];
  folders.forEach((item) => {
    var num = parseInt(item.name.split("-")[0]);
    toc[num] = item.name;
  });
  
  // Sort toc by key
  var sorted = Object.keys(toc)
    .sort()
    .reduce((obj, key) => {
      obj[key] = toc[key];
      return obj;
    }, {});

  // Generate the table of solutions
  let solutionsTable = `
<!-- SOLUTIONS TABLE BEGIN -->
| Leetcode Problem | Problem Statement | Solution |
|---:|:-----|:----:|
`;
  for (var key in sorted) {
    var str = sorted[key].split("-");
    var name = str.slice(1).map(word => {
      const lowerCaseWord = word.toLowerCase();
      if (["in", "of", "for", "and", "or", "the", "a", "an", "to", "by", "at", "from", "on", "off", "up", "down", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"].includes(lowerCaseWord)) {
        return lowerCaseWord;
      } else if (lowerCaseWord.startsWith("i") && lowerCaseWord.length <= 3) {
        return lowerCaseWord.toUpperCase();
      } else {
        return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
      }
    }).join(" ");
    name = name.charAt(0).toUpperCase() + name.slice(1);
    var num = key;
    var folderName = str.join("-");
    var fileName = str.slice(1).join("-") + ".java";
    var solutionPath = `./${folderName}/${fileName}`;
    solutionsTable += `| [${num}](https://leetcode.com/problems/${str.slice(1).join("-")}/) | ${name} | [Solution](${solutionPath}) |\n`;
  }
  solutionsTable += "<!-- SOLUTIONS TABLE END -->";

  // Read the existing README content
  const readmePath = path.join(__dirname, "..", "README.md");
  let readmeContent = fs.readFileSync(readmePath, "utf8");

  // Check if the solutions table already exists
  if (readmeContent.includes("<!-- SOLUTIONS TABLE BEGIN -->")) {
    // Replace the existing table
    readmeContent = readmeContent.replace(
      /<!-- SOLUTIONS TABLE BEGIN -->[\s\S]*<!-- SOLUTIONS TABLE END -->/,
      solutionsTable
    );
  } else {
    // Find the "## Solutions" heading and insert the table after it
    const solutionsHeading = "## Solutions";
    const headingIndex = readmeContent.indexOf(solutionsHeading);
    if (headingIndex !== -1) {
      const insertIndex = headingIndex + solutionsHeading.length;
      readmeContent = readmeContent.slice(0, insertIndex) + "\n\n" + solutionsTable + readmeContent.slice(insertIndex);
    } else {
      console.error("Could not find '## Solutions' heading in README.md");
      return;
    }
  }

  // Write the updated content back to README.md
  fs.writeFileSync(readmePath, readmeContent);
  console.log("README.md has been updated with the solutions table!");
};

toc();