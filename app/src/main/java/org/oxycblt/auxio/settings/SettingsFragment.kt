/*
 * Copyright (c) 2021 Auxio Project
 * SettingsFragment.kt is part of Auxio.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.oxycblt.auxio.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.oxycblt.auxio.databinding.FragmentSettingsBinding
import org.oxycblt.auxio.util.applyEdge

/**
 * A container [Fragment] for the settings menu.
 * @author OxygenCobalt
 */
class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater)

        binding.settingsToolbar.apply {
            setOnMenuItemClickListener {
                findNavController().navigate(
                    SettingsFragmentDirections.actionShowAbout()
                )

                true
            }

            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        binding.applyEdge { bars ->
            binding.root.updatePadding(left = bars.left, right = bars.right)
            binding.settingsAppbar.updatePadding(top = bars.top)

            // The padding + clipToPadding method does not seem to work with a
            // FragmentContainerView. Do it directly in SettingsListFragment instead.
        }

        binding.settingsAppbar.liftOnScrollTargetViewId = androidx.preference.R.id.recycler_view

        return binding.root
    }
}
