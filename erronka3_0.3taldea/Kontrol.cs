using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace erronka3_0._3taldea
{
    internal class Kontrol
    {

        public Kontrol() { }
        public Boolean LoginEgin(TextBox txtNombre, TextBox txtPassword) {

            Boolean loginEginDu = Bezeroa.loginEginDezake(txtNombre.Text, txtPassword.Text);

            return loginEginDu;

        }
    }
}
