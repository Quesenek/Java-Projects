package Que1;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.utils.Client;

//Pokedex API wraper for pokeapi.co || https://github.com/oscar0812/pokeapi-v2-java
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Pokemon fPoke = Client.getPokemonById(1);
        Pokemon[] Poke = new Pokemon[893];

        String pokeList = "";

        for (int i = 0; i < Poke.length; i++) 
        {
            Poke[i] = Client.getPokemonById(i+1);
        }
        
        pokeList += String.format("ID: %s\nName: %s\nHeight: %s\nWeight: %s\n", Poke[0].getId(), Poke[0].getName(), Poke[0].getHeight(), Poke[0].getWeight());

        System.out.println(pokeList);
    }
}
