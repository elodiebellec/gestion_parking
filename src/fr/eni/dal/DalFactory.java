package fr.eni.dal;

public class DalFactory {


        public static PersonneDal getPersonneDalFactory()
        {
            return new PersonneDaoBdd();
        }

        public static VoitureDal getVoitureDalFactory()
        {

            return new VoitureDaoBdd();
        }



}
