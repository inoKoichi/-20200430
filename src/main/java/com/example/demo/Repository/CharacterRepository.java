package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
/**
 * CharacterRepository
 * DB access
 */
public interface CharacterRepository extends JpaRepository<Characters, Long> {
  Characters findByCharacterIdEquals(Integer characterId);

  // 奇数IDを取得
  @Query(value = "SELECT * FROM characters WHERE character_id %2 != 0", nativeQuery = true)
  List<Characters> findByOddCharacterId();

  // 偶数IDを取得
  @Query(value = "SELECT * FROM characters WHERE character_id %2 = 0", nativeQuery = true)
  List<Characters> findByEvenCharacterId();

  //所属1か3を取得
  @Query(value = "SELECT * FROM characters WHERE a_member_of = 1 OR a_member_of =3", nativeQuery = true)
  List<Characters> findByExorcistAndNoah();

  //所属2か4を取得
  @Query(value = "SELECT * FROM characters WHERE a_member_of = 2 OR a_member_of =4", nativeQuery = true)
  List<Characters> findByOthers();

  //所属1か2を取得
  @Query(value = "SELECT * FROM characters WHERE a_member_of = 1 OR a_member_of =2", nativeQuery = true)
  List<Characters> findByBlackOrder();

  //所属3か4を取得
  @Query(value = "SELECT * FROM characters WHERE a_member_of = 3 OR a_member_of =4", nativeQuery = true)
  List<Characters> findByNoahAndOthers();
}
