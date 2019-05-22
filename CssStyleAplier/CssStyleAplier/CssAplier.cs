using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CssStyleAplier
{
    public class CssAplier
    {
        StreamReader stream;
        string projectDirectory = Directory.GetParent(Assembly.GetExecutingAssembly().Location).Parent.Parent.FullName;
        CssAplier()
        {
        }
        public void ApplyStyleFor(object Object)
        {
            Console.WriteLine(Object.GetType());

            stream = new StreamReader(projectDirectory + "/Styles.css");
            // Splits text into the name of control and its properties one after another
            string[] strings = stream.ReadToEnd().Split(new char[]{ '{', '}' });
            for (int i = 0; i < strings.Length; i += 2)
            {
                if (strings[i] == Object.GetType().ToString().ToLower())      // Finds properties of the necessary control
                {
                    if (i + 1 < strings.Length)
                    {
                        switch (Object.GetType().ToString())                  // Gives control to the necessary method
                        {
                            case "button":
                                ReadStyleFor((Button)Object, strings[i + 1]);
                                break;
                            case "label":
                                ReadStyleFor((Label)Object, strings[i + 1]);
                                break;
                            case "textbox":
                                ReadStyleFor((TextBox)Object, strings[i + 1]);
                                break;
                            case "checkbox":
                                ReadStyleFor((CheckBox)Object, strings[i + 1]);
                                break;
                            case "combobox":
                                ReadStyleFor((ComboBox)Object, strings[i + 1]);
                                break;
                            case "radiobutton":
                                ReadStyleFor((RadioButton)Object, strings[i + 1]);
                                break;
                            case "listbox":
                                ReadStyleFor((ListBox)Object, strings[i + 1]);
                                break;
                            default:
                                break;
                        }
                    }
                    return;
                }
            }
        }
        private void ReadStyleFor(Button control, string properties)
        {
            Button button;
            button.Font = new Font();
        }
        private void ReadStyleFor(Label control, string properties)
        {

        }
        private void ReadStyleFor(TextBox control, string properties)
        {

        }
        private void ReadStyleFor(CheckBox control, string properties)
        {

        }
        private void ReadStyleFor(ComboBox control, string properties)
        {

        }
        private void ReadStyleFor(RadioButton control, string properties)
        {

        }
        private void ReadStyleFor(ListBox control, string properties)
        {

        }
    }
}
